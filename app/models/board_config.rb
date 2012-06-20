class BoardConfig
  include DataMapper::Resource

  property :id, Serial
  property :map_url, String, :required => false, :format => /#{VenueConfig::URL_PATTERN}/, :length => 255, :message => VenueConfig::FORMAT_MESSAGE
  property :directions_url, String, :required => false, :format => /#{VenueConfig::URL_PATTERN}/, :length => 255, :message => VenueConfig::FORMAT_MESSAGE
 property :transportation_url, String, :required => false, :format => /#{VenueConfig::URL_PATTERN}/, :length => 255, :message => VenueConfig::FORMAT_MESSAGE
 
  #property :board_id, Integer, :required => true
  #property :locale_id, Integer, :required => true
  belongs_to :locale
  belongs_to :board

  timestamps :at

  belongs_to :modified_by, 'User'

  # require 'dm-serializer'
  # alias :to_x :to_xml_document
  # def to_xml_document(opts = {}, doc = nil)
  #   unless(opts[:methods])
  #     opts.merge!({:methods => [:updated_by], :updated_by => {:methods => [], :exclude => [:created_at, :updated_at]}})
  #   end
  #   to_x(opts, doc)
  # end

end
