class Error

  include DataMapper::Resource

  property :id, Serial

  property :message, String, :required => true, :length => 255
  property :request, Text, :required => true, :length => 64536
  property :response, Text, :required => true, :length => 32768
  property :session, Text, :required => true, :length => 16384
  property :parameters, Text, :required => true, :length => 32768
  property :clazz, String, :required => true, :length => 64
  property :backtrace, Text, :required => true, :length => 32768

  timestamps :at

end
