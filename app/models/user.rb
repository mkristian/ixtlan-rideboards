class User

  include DataMapper::Resource

  property :id, Serial, :auto_validation => false
  
  property :login, String, :required => true, :unique => true, :length => 32
  property :name, String, :required => true, :length => 128
  property :updated_at, DateTime, :required => true
  
  attr_accessor :groups, :applications

  # do not record timestamps since they are set from outside
  def set_timestamps_on_save
  end

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

end
