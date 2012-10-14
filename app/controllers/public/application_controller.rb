require 'param_filter'
require 'dm-rails/middleware/identity_map'

class Public::ApplicationController < ActionController::Base

  use Rails::DataMapper::Middleware::IdentityMap

  layout "public"

  clear_helpers

  protect_from_forgery

  skip_before_filter :authorize, :check_session_expiry

  protected

  def filter
    @_filter ||= ParamFilter.new
  end

  def session_timeout
    raise "should not be used"
  end
  # p before_filters

  def setup
    venue = Venue.for_name(params[:venue])
    if venue
      @board = venue.board_for(params[:board])
      if @board
        @lang = venue.locale_for(params[:lang])
        if @lang
          @path_prefix = url_for(:controller => "public/boards",
                                 :action => :show, 
                                 :venue => venue.name,
                                 :board => @board.name,
                                 :lang => @lang)
          @board.venue.locale = @lang
          FastGettext.set_locale(@lang)
          if FastGettext.translation_repositories.key?(venue.name)
            FastGettext.text_domain = venue.name
          end
          Listing.cleanup
          true
        else
          @lang = venue.default_locale
          redirect(@lang, venue, @board)
          false
        end
      else
        render_not_found(venue)
        false
      end
    else
      render_not_found(venue)
      false
    end
  end

  def redirect(lang, venue, board = venue.boards.first)
    if board
      redirect_to url_for(:controller => "public/boards", 
                          :action => :show,
                          :venue => venue.name,
                          :board => board.name,
                          :lang => lang)
      board
    else
      render_not_found(venue)
      "- error: venue '#{venue.name}' has no boards"
    end
  end

  def render_not_found(venue = nil)
    if venue
      render :template => "public/noboard", :layout => "error", :status => 404
    else
      render :template => "public/novenue", :layout => "error", :status => 404
    end
  end
end

  # def public_filter
  #   @lang, @venue, @board = Venue.retrieve(params[:lang] || language_from_browser, params[:venue], params[:board] || params[:id] || params[:board_id])
  #   if(@venue)
  #     p (params[:board_id].nil? && params[:id].nil?)
  #     p (params[:board] != @board.name && params[:id] != @board.id.to_s && params[:board_id] != @board.id.to_s)
  #     if(params[:lang].nil? || params[:venue].nil? || (params[:id].nil? && params[:board_id].nil?) || params[:lang] != @lang.code || (params[:board] != @board.name && params[:id] != @board.id.to_s && params[:board_id] != @board.id.to_s))
  #       puts "-" * 80
  #       p @lang
  #       p @venue
  #       p @board
  #       redirect_to url_for(:controller => :public_boards, 
  #                           :action => :show, 
  #                           :venue => @venue.name,
  #                           :id => @board.id,
  #                           :lang => @lang.code)
  #       false
  #     elsif(@board.nil? || @lang.nil?)
  #       render_not_found
  #     else
  #       # TODO session tracking for venues with password
  #       @path_prefix = url_for(:controller => :public_boards, 
  #                           :action => :show, 
  #                           :venue => @venue.name,
  #                           :board => @board.name,
  #                           :lang => @lang.code)
  #       @venue.locale = @lang
  #       true
  #     end
  #   else
  #     # check for php pages
  #     php = File.join(RAILS_ROOT, 'public', params[:venue], "index.php")
  #     if (File.exists?(php))
  #       redirect_to "/#{params[:venue]}/index.php"
  #       false
  #     else
  #       render_not_found
  #     end
  #   end
  # end

  # def language_from_browser
  #   #get prefered browser language
  #   #TODO needs find the language from the browser which fits into
  #   #the venue's languages
  #   accept = request.env["HTTP_ACCEPT_LANGUAGE"]
  #   if accept
  #     lang = accept.split(",")[0]
  #     lang = lang[0..1]
  #   end
    
  #   # if no language could be found, fall back to default
  #   unless lang
  #     lang = 'en' #TODO @venue.default_language
  #   end   
  #   lang
  # end
#end
