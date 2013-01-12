require 'ixtlan/user_management/authentication_model'
require 'ixtlan/gettext/models'

Rideboards::Application.config.rest.server( :users ) do |server|
  config = Ixtlan::Passwords.get( :rest ).get( :users )
  server.url = config.get( :url, "http://localhost:3000" )
  server.options[ :headers ] = {'X-Service-Token' => config.get( :token, 'behappy' )}

  server.add_model( User )
  server.add_model( Domain )
  server.add_model( Ixtlan::UserManagement::Authentication, :authentications )
end

Rideboards::Application.config.rest.server( :gettext ) do |server|
  config = Ixtlan::Passwords.get( :rest ).get( :gettest )
  server.url = config.get( :url, "http://localhost:3000" )
  server.options[ :headers ] = {'X-Service-Token' => config.get( :token, 'be happy' )}

  server.add_model( Locale )
  server.add_model( Ixtlan::Gettext::Translation, config[:translations_path] || "translations/uncommitted" )
  server.add_model( Ixtlan::Gettext::TranslationKey, config[:translation_keys_path] || "translation_keys/uncommitted" )
end

Rideboards::Application.config.rest.server( :local ) do |server|
  config = Ixtlan::Passwords.get( :rest ).get( :local )
  server.url = config[:url] || "http://localhost:3000"

  server.add_model( Ixtlan::Gettext::Flush, "gettext/flush" )
end
