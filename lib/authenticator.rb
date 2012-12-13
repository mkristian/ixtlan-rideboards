require 'ixtlan/user_management/authenticator'
require 'ixtlan/user_management/dummy_authentication'
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
if Ixtlan::UserManagement::DummyAuthentication.need_dummy?( Rideboards::Application.config.rest, 'users' )

  class Authenticator

    include Ixtlan::UserManagement::DummyAuthentication

    def user_model
      User
    end

    def setup_group( login )
      g = group_for( Group, login )
      g.domains = Domain.all( :name => split( login ) )
      g
    end
  end

end
