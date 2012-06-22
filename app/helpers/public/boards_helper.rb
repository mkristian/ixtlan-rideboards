# Methods added to this helper will be available to all templates in the application.
module Public::BoardsHelper

  def to_url(id_or_action, action = nil)
    if action
      url_for(:id => id_or_action,
              :venue => @board.venue.name, 
              :board => @board.name, 
              :lang => @lang, 
              :controller => 'public/listings', 
              :action => action)
    else
      url_for(:venue => @board.venue.name, 
              :board => @board.name, 
              :lang => @lang, 
              :controller => 'public/listings', 
              :action => id_or_action)
    end
  end
end
