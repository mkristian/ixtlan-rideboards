class Listing
  include DataMapper::Resource

  property :id, Serial
  property :location, String, :required => true, :format => /^[^<">]*$/, :length => 255, :message => "Please enter a valid Location"
  property :email, String, :required => true, :length => 255, :message => "Please enter a valid Email", :format => :email_address
  property :name, String, :required => true, :format => /^[^<&">]*$/, :length => 255, :message => "Please enter a valid Name"
  property :ridedate, Date, :required => true

  validates_with_block :ridedate do
    if ridedate < Date.today
      [ false, "Ride Date must be in the future"]
    else
      true
    end
  end

  property :driver, Boolean, :required => true
  property :password, String, :required => true, :format => /^[0-9]*$/, :length => 5
  timestamps :at

  belongs_to :board

  def self.cleanup
    begin
      Listing.all(:ridedate.lt => DateTime.now).destroy!
    rescue Exception => e
      # TODO log exception
      puts e.message
    end
  end

  def send_confirmation(base_url, lang)
    url = board.venue.iframe_url
    if url.blank? 
      url = base_url
    end
      
    Mailer.confirm(lang, self, url).deliver
  end

  def send_reminder(base_url, lang)
    url = board.venue.iframe_url
    if url.blank? 
      url = base_url
    end
      
    Mailer.remind(lang, self, url).deliver
  end

  def reset_password
    if(email == "test@rides.server.dhamma.org")
      self.password = "31415"
    else
      self.password = generate_password(5)
    end
  end

  def new_contact(*args)
    Contact.new(self, *args)
  end

  private

  def generate_password(length = 12)
    password = Array.new(length).map { (48 + rand(10)).chr }.join
    password = generate_password(length) unless password =~ /^[0-9]+$/
    password
  end
end
