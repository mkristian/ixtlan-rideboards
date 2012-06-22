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

  def send_message(lang)
    if valid?
      message = Mailer.contact(lang, self)

      # sent the message
      message.deliver
      
      # finally extract the message content for displaying
      # after clearing all the visual noise
      message.encoded.gsub(/^[a-zA-Z\-]+: .*\n/, '').sub(/\s*charset=[a-zA-Z\-0-9]+.*\n/,'').sub(/\n/, '').strip

    end
  end
end
