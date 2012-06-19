require 'ixtlan/guard/abstract_session'
require 'heartbeat'

class Session < Ixtlan::Guard::AbstractSession
  include ActiveModel::Serializers::JSON
  include ActiveModel::Serializers::Xml

  # TODO bug in vellam/babel which do need to use attributes instead of value when decending the object tree
  def attributes
    {'idle_session_timeout' => idle_session_timeout, 'permissions' => permissions}
  end

  def errors # needed for respond_with
    []
  end

  def self.authenticate(login, password)
    begin
      auth = Authentication.create(:login => login, :password => password)
      user = User.find_by_login(auth.login)
      if user.nil?
        heart = Heartbeat.new
        heart.beat User
        user = User.find_by_login(auth.login)
        raise "user #{auth.login} not found" unless user
      end
      user.name = auth.name
      user.groups = auth.groups
      user.applications = auth.applications
      user
    rescue ActiveResource::ResourceNotFound
      result = User.new
      result.log = "access denied #{login}" # error message
      result
    rescue ActiveResource::UnauthorizedAccess
      result = User.new
      result.log = "access denied #{login}" # error message
      result
    end
  end
end

unless Rideboard::Application.config.respond_to?(:remote_service_url)
  module DummyAuthentication

    def self.included(session)

      session.class_eval do
        def self.authenticate(login, password)
          result = User.new
          if password.blank?
            result.log = "no password given with login: #{login}"
          elsif login.blank?
            result.log = "no login given"
          elsif password == "behappy"
            if u = User.get!(1)
              result = u
            else
              result.login = login
              result.name = login.humanize
              result.id = 1
              result.updated_at = DateTime.now
            end
            result.groups = [Group.new('name' => login)]
            result.applications = []
          else
            result.log = "wrong password for login: #{login}"
          end
          result
        end
      end
    end
  end

  Session.send(:include, DummyAuthentication)
end
