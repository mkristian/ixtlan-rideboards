class Venue
  include DataMapper::Resource

  property :id, Serial
  property :fullname, String, :required => true, :format => /^[^<'&">]*$/, :length => 255
  property :email, String, :required => true, :format => /^rides@([a-z0-9-]+[.])+dhamma.org$/, :message => "must be of format: rides@*dhamma.org", :length => 255
  property :password, String, :required => false, :format => /^[^<'&">]*$/, :length => 255
  property :bcc, Boolean, :required => false, :default => false
  property :enabled, Boolean, :required => true

  timestamps :at

  belongs_to :modified_by, 'User'

  belongs_to :center

  has n, :venue_configs
  has n, :boards, :order => :position
  
  # require 'dm-serializer'
  # alias :to_x :to_xml_document
  # def to_xml_document(opts = {}, doc = nil)
  #   unless(opts[:methods])
  #     opts.merge!({:methods => [:updated_by], :updated_by => {:methods => [], :exclude => [:created_at, :updated_at]}})
  #   end
  #   to_x(opts, doc)
  # end

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
    center.name
  end
  
  def languages
    venue_configs.collect do |vc|
      vc.locale.code
    end
  end

  def self.for_name(name)
    Venue.first(Venue.center.name => name, :enabled => true)
  end

  # def self.retrieve(lang_code, venue_name, board_name)
  #   if(!lang_code.nil? && lang_code.size != 2 && board_name.nil?)
  #     retrieve(nil, lang_code, venue_name)
  #   else
  #     # TODO better query
  #     venue = all().detect{ |v| v.center.name == venue_name}
  #     board = venue.nil? ? nil : (venue.board_for(board_name) || venue.boards.first)
  #     locale = venue.nil? ? nil : (venue.locale_for(lang_code) || venue.venue_configs.first.locale)
  #     [locale, venue, board]
  #   end
  # end
end
