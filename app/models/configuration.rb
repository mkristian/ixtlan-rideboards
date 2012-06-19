class Configuration

  include DataMapper::Resource

  property :id, Serial

  property :errors_keep_dumps, Integer, :required => true
  property :errors_base_url, String, :required => false, :length => 128
  property :errors_from_email, String, :required => false, :length => 128
  property :errors_to_emails, String, :required => false, :length => 255
  property :idle_session_timeout, Integer, :required => true
  property :audits_keep_logs, Integer, :required => true

  timestamps :at

  belongs_to :modified_by, "User"

  def self.instance
    self.first || self.new
  end

end
