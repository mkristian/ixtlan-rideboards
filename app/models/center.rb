class Center

  include DataMapper::Resource

  property :id, Serial  
  property :name, String, :format => /^[a-z]+$/,:required => true, :length => 32

  timestamps :updated_at

end
