class Locale

  include DataMapper::Resource

  property :id, Serial  
  property :code, String, :required => true , :format => /^[a-z][a-z](_[A-Z][A-Z])?$/, :length => 7, :unique_index => true

  timestamps :updated_at

end
