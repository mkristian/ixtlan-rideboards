class Audit

  include DataMapper::Resource

  property :id, Serial

  property :login, String, :required => true, :length => 32
  property :message, String, :required => true, :length => 255, :format => /^[^<>]*$/

  property :created_at, DateTime, :required => true, :lazy => true

end
