require 'ixtlan/remote/sync'
class Updater < Ixtlan::Remote::Sync

  def initialize
    super Rideboards::Application.config.rest
    register( User )
    register( Locale )
    register( Domain )
    register( Ixtlan::Gettext::TranslationKey )
    register( Ixtlan::Gettext::Translation ) do |s|
      if s.count > 0
        Ixtlan::Gettext::Flush.trigger( Rideboards::Application.config.rest )
      end
    end
  end

end
