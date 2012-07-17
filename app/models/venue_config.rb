class VenueConfig
  include DataMapper::Resource

  FORMAT_MESSAGE = "must be of the format: http://*.dhamma.org/* or https://*.dhamma.org/*"
  URL_PATTERN = /^https?:\/\/(\w+:\w+@)?([a-z-]+[.])+dhamma.org\/?.*$|^\s*$/
  PATTERN = /^[^<'&">]*$/

  property :id, Serial

  property :date_format, String, :required => true, :format => PATTERN, :length => 255, :default => "%Y-%M-%d"
  property :home_url, String, :required => true, :format => :url, :length => 255
  property :schedule_url, String, :required => false, :format => :url, :length => 255
  property :checklist_url, String, :required => false, :format => :url, :length => 255
  property :iframe_url, String, :required => false, :format => :url, :length => 255

  belongs_to :locale
  belongs_to :venue

  timestamps :at

  belongs_to :modified_by, 'User'

  validates_format_of :date_format, :with => PATTERN, :when => [ :strict ]
  validates_format_of :home_url, :schedule_url, :iframe_url, :checklist_url, :with => URL_PATTERN, :message => FORMAT_MESSAGE  :when => [ :strict ]
  validates_presence_of :date_format, :home_url, :center_id, :modified_by_id, :when => [ :strict ]

  alias :venue_valid? :valid?
  def valid?
    venue_valid?((venue && venue.strict_domain_names) ? :strict : :default)
  end
end
