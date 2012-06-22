class Board
  include DataMapper::Resource

  property :id, Serial
  property :name, String, :required => true, :format => /^[^<'&">]*$/, :length => 255
  property :fullname, String, :required => true, :format => /^[^<'&">]*$/, :length => 255
  property :position, Integer, :required => true
  property :enabled, Boolean, :required => true
  timestamps :at

  belongs_to :modified_by, 'User'

  belongs_to :venue

  has n, :board_configs
  has n, :listings

  def new_listing_offer
    listing = Listing.new
    listing.driver = true
    listing.board = self
    listing
  end

  def new_listing_request
    listing = Listing.new
    listing.driver = false
    listing.board = self
    listing
  end

  def create_listing(date, params)
    listing = Listing.new(params)
    listing.ridedate = date
    listing.board = self
    listing.reset_password
     
    if Listing.first(:board => listing.board, 
                     :name => listing.name, 
                     :location => listing.location, 
                     :email => listing.email, 
                     :ridedate => listing.ridedate, 
                     :driver => listing.driver)
      nil
    else
      listing.save
      listing
    end
  end

  def delete_listing(id, password)
    if l = listings.get(id)
      if password.to_s == l.password.to_s
        l.destroy
        l
      else
        nil
      end
    end
  end

  # require 'dm-serializer'
  # if protected_instance_methods.find {|m| m == 'to_x'}.nil?
  #   alias :to_x :to_xml_document
  #   def to_xml_document(opts = {}, doc = nil)
  #     unless(opts[:methods])
  #       opts.merge!({
  #                     :skip_types => true,
  #                     :skip_empty_tags => true,
  #                     :methods => [:created_by, :updated_by, :listings],
  #                     :listings =>{
  #                       :exclude => [:created_at, :updated_at, :password, :board_id],
  #                       :methods => []
  #                     }
  #                   })
  #     end
  #     unless(opts[:exclude])
  #       opts.merge!({:exclude => [:created_by_id, :updated_by_id]})
  #     end
  #     to_x(opts, doc)
  #   end
  # end

  def locale= (locale, create = false)
    @lang = BoardConfig.first(:board => self, :locale => locale)

    # create a new one if needed and wanted
    @lang = BoardConfig.create(:locale => locale, :board => self) if @lang.nil? && create
  end

  #create delegate methods
  %w(directions_url map_url transportation_url).each do |name|
    class_eval <<-CODE, __FILE__, __LINE__
      def #{name}
        @lang.#{name} if @lang
      end

      def #{name}?
        !(@lang && @lang.#{name}).blank?
      end
CODE
  end
end
