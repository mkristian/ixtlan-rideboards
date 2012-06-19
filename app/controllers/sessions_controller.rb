class SessionsController < ApplicationController

  skip_before_filter :authorize

  skip_before_filter :check_session, :only => :destroy

  prepend_after_filter :reset_session, :only => :destroy

  public

  def create
    auth = params[:authentication] || params
    @session = serializer(Session.create(auth[:login] || auth[:email], 
                                         auth[:password]))
    if @session.valid?
      current_user(@session.user)
      @session.idle_session_timeout = 
        Rideboard::Application.config.idle_session_timeout
      @session.permissions = guard.permissions(current_groups)

      respond_with(@session)
    else
      head :unauthorized
    end
  end

  def reset_password
    authentication = params[:authentication] || []
    user = User.reset_password(authentication[:email] || authentication[:login])

    if user

      # for the log
      @session = user
      
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
