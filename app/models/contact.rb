class Contact

  include ActiveModel::Validations

  attr_accessor :name, :email, :phone, :listing

  validates :name, :format => { :with => /^[^<&">]+$/, :message => "Please fill in a valid name" }
  validates :email, :format => { :with => /^[a-z0-9\-_+\.]+\@([a-z0-9\-]+\.)+[a-z0-9]{2,4}$/i, :message => "Please fill in a valid email address" }
  validates :phone, :format => { :with => /^[0-9\-() +]*$/, :message => "Please fill in a valid phone number" }

  def initialize(listing, contact = {})
    @name = contact[:name]
    @email = contact[:email]
    @phone = contact[:phone]
    @listing = listing
  end

  def send_message
    Mailer.contact( self ) if valid?
  end
end
