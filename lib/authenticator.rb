require 'ixtlan/user_management/authenticator'
class Authenticator < Ixtlan::UserManagement::Authenticator

  def initialize
    super Rideboards::Application.config.rest
  end

  def user_new( params = {} )
    groups = params.delete( 'groups' ) || []
    u = User.new( params )
    u.groups = groups.collect { |g| Group.new( g ) }
    u
  end
end

# only dev mod without SSO needs a dummy authentication
if Rideboards::Application.config.rest.to_server( 'users' ).url =~ /localhost/ && !(ENV['SSO'] == 'true' || ENV['SSO'] == '')

  module DummyAuthentication

    def login(login, password)
      result = nil
      if ! login.blank? && password.blank?
        if u = User.get!(1)
          result = u
        else
          result.id = 1
          result.updated_at = DateTime.now
        end
        result.login = login.sub( /\[.*/, '' )
        result.name = result.login.humanize
        g = Group.new('name' => result.login )
        lids = []
        login.sub(/.*\[/,'').sub(/\].*/,'').split(/,/).each do |id| 
          if id.length == 2
            lids << id
          end
        end
        g.locales = Locale.all(:code => lids)
        result.groups = [g]
        result.applications = []
      end
      result
    end
  end

  require 'authenticator'
  Authenticator.send(:include, DummyAuthentication)
end
