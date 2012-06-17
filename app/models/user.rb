class User < ActiveRecord::Base

  attr_accessor :groups, :applications

  validates :login, :presence => true

  record_timestamps = false

  def self.authenticate(login, password)
    result = User.new
    if password.blank?
      result.log = "no password given with login: #{login}"
    elsif login.blank?
      result.log = "no login given"
    elsif password == "behappy"
      result.login = login
      result.name = login.humanize
      result.id = 0
      result.groups = [Group.new('name' => login)]
      result.applications = []
    else
      result.log = "wrong password for login: #{login}"
    end
    result
  end

  def self.reset_password(login)
    result = User.new(:login => login)
    begin
      Authentication.post(:reset_password, :login => login)
    rescue ActiveResource::ResourceNotFound
      result.log = "User(#{login}) not found"
    end
    result
  end

  def log=(msg)
    @log = msg
  end

  def to_log
    if @log
      @log
    else
      "User(#{id ? (id.to_s + ':') : ''}#{login})"
    end
  end

  unless respond_to? :old_as_json
    alias :old_as_json :as_json
    def as_json(options = nil)
      options = { :methods => [ :applications ] } unless options
      old_as_json(options)
    end
  end

end
