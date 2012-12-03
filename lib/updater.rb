require 'ixtlan/remote/sync'
class Updater < Ixtlan::Remote::Sync

  def initialize
    super Rideboards::Application.config.rest
    register( User )
    register( Locale )
    register( Domain )
    register( Ixtlan::Gettext::Translation )
  end

end
