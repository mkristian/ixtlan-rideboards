require 'ixtlan/user_management/authentication_model'
require 'ixtlan/gettext/models'

rest = Rideboards::Application.config.rest

REST_CONFIG = CONFIG[:rest] || {}

rest.server( :users ) do |server|
  u = REST_CONFIG[:users] || {}
  server.url = u[:url] || "http://localhost:3000"
  server.options[:headers] = {'X-Service-Token' => u[:token] || 'behappy'}
  server.add_model( User )
  server.add_model( Domain )
  server.add_model( Ixtlan::UserManagement::Authentication, :authentications )
end

rest.server( :gettext ) do |server|
  g = REST_CONFIG[:gettext] || {}
  server.url = g[:url] || "http://localhost:3000"
  server.options[:headers] = {'X-Service-Token' => g[:token] || 'be happy'}
  server.add_model( Locale )
  server.add_model( Ixtlan::Gettext::Translation, "translations/uncommitted" )
  server.add_model( Ixtlan::Gettext::TranslationKey, :translation_keys )
end
