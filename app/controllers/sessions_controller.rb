class SessionsController < ApplicationController

  skip_before_filter :authorize

  skip_before_filter :check_session, :only => :destroy

  prepend_after_filter :reset_session, :only => :destroy

  private

  def authenticator
    @_auth ||= Authenticator.new
  end

  def login_and_password
    auth = params[:authentication] || params
    @session = auth[:login] || auth[:email]
    [ @session, auth[:password] ]
  end

  public

  def create
    user = authenticator.login( *login_and_password )
    if user      
      current_user( user )
      @session = serializer( Session.new( 'user' => user,
                                          'idle_session_timeout' => Rideboards::Application.config.idle_session_timeout,
                                          'permissions' => guard.permissions( user.groups) ) )

      respond_with(@session)
    else
      @session = "access denied #{@session}"
      head :unauthorized
    end
  end

  def reset_password
    if @session = authenticator.reset_password( login_and_password[ 0 ] )
      head :ok
    else
      head :not_found
    end
  end

  def destroy
    # for the log
    @session = current_user

    # reset session happens in the after filter which allows for 
    # audit log with username which happens in another after filter
    head :ok
  end
end
