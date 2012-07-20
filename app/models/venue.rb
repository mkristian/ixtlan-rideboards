module Encoding
  class CompatibilityError < StandardError; end
end
class Venue
  include DataMapper::Resource

  property :id, Serial
  property :fullname, String, :required => true, :format => /^[^<'&">]*$/, :length => 255
  property :email, String, :required => true, :format => :email_address, :length => 255
  property :password, String, :required => false, :format => /^[^<'&">]*$/, :length => 255
  property :bcc, Boolean, :required => false, :default => false
  property :enabled, Boolean, :required => true
  property :strict_domain_names, Boolean, :required => true

  timestamps :at

  belongs_to :modified_by, 'User'

  belongs_to :domain
  property :domain_id, Integer, :required => false, :default => 1

  has n, :venue_configs
  has n, :boards, :order => :position

  validates_format_of :email, :with => /^rides@([a-z0-9-]+[.])+dhamma.org$/, :message => "must be of format: rides@*dhamma.org", :when => [ :strict ]
  validates_presence_of :fullname, :email, :enabled, :strict_domain_names, :domain_id, :modified_by_id, :when => [ :strict ]

  alias :venue_valid? :valid?
  def valid?(context = nil)
    venue_valid?(context || (strict_domain_names ? :strict : :default))
  end

  # sets a state of the current language for the venue, this allows
  # the getter of the respective url to return the right language dependent url
  def locale=(code, create = false)
    require 'venue_config'
    @lang = VenueConfig.first(:venue => self, :locale => code)

    # create a new one if needed and wanted
    @lang = VenueConfig.create(:venue => self, :locale => code) if @lang.nil? && create

    if @lang
      boards.each do |b|
        b.locale = @lang.locale
      end
    end
  end
  
  #create delegate methods
  %w(home_url iframe_url checklist_url schedule_url date_format).each do |name|
    class_eval <<-CODE, __FILE__, __LINE__
      def #{name}
        @lang.#{name} if @lang
      end
CODE
    if name =~ /_url$/
class_eval <<-CODE, __FILE__, __LINE__
      def #{name}?
        !(@lang && @lang.#{name}).blank?
      end
CODE
    end
  end

  def board_for(name)
    boards.detect do |b|
      # allow name as well id !!
      b.name == name || b.id.to_s == name
    end
  end

  def default_locale
    venue_configs.first.locale.code
  end

  def locale_for(code)
    v = venue_configs.detect do |vc|
      vc.locale.code == code
    end
    code if v
  end

  def protected?
    password
  end

  def name
    domain.name
  end
  
  def languages
    venue_configs.collect do |vc|
      vc.locale.code
    end
  end

  def self.for_name(name)
    Venue.first(Venue.domain.name => name, :enabled => true)
  end
end
