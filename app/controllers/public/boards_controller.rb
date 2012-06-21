class Public::BoardsController < Public::ApplicationController

  private

  def language_from_browser
    #get prefered browser language
    #TODO needs find the language from the browser which fits into
    #the venue's languages
    accept = request.env["HTTP_ACCEPT_LANGUAGE"]
    if accept
      lang = accept.split(",")[0]
      lang = lang[0..1]
    end
    
    lang
  end

  public

  # GET /:venue
  def venue
    venue = Venue.for_name(params[:venue])
    if venue
      lang = language_from_browser || venue.default_locale
      @board = redirect(lang, venue)
    else
      # check for php pages
      php = File.join(Rails.root, 'public', params[:venue], "index.php")
      if File.exists?(php)
        redirect_to "/#{params[:venue]}/index.php"
      else
        @board = "- error: venue '#{params[:venue]}' not found"
        render_not_found
      end
    end
  end

  # GET /:lang_venue
  def lang_venue
    venue = Venue.for_name(params[:venue])
    if venue
      lang = venue.locale_for(params[:lang]) || venue.default_locale
      @board = redirect(lang, venue)
    else
      @board = "- error: venue '#{params[:venue]}' not found"
      render_not_found
    end
  end

  # GET /:lang/:venue/:boards
  def show
    if setup
      respond_to do |format|
        format.html { render }
        format.rss { render :layout => false }
      end
    else
      @board = "- error: board not found"
    end
  end
end
