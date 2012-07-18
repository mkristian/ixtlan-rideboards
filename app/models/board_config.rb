class BoardConfig
  include DataMapper::Resource

  property :id, Serial
  property :map_url, String, :required => false, :format => :url, :length => 255
  property :directions_url, String, :required => false, :format => :url, :length => 255
 property :transportation_url, String, :required => false, :format => :url, :length => 255
 
  belongs_to :locale
  belongs_to :board

  timestamps :at

  belongs_to :modified_by, 'User'

  validates_format_of :map_url, :directions_url, :transportation_url, :with => VenueConfig::URL_PATTERN, :message => VenueConfig::FORMAT_MESSAGE, :when => [ :strict ]

  alias :venue_valid? :valid?
  def valid?
    venue_valid?((board && board.venue.strict_domain_names) ? :strict : :default)
  end
end
