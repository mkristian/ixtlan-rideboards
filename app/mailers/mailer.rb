
class Mailer < ActionMailer::Base
  
  def contact(lang, contact)
    @contact = contact
    config = { 
      :from => "#{contact.listing.board.venue.fullname} <#{contact.listing.board.venue.email}>",
      :to => "#{contact.listing.name} <#{contact.listing.email}>", 
      :subject => "Rideshare contact information" + (contact.listing.board.venue.bcc ? " for #{contact.listing.name} <#{contact.listing.email}>" : ""),
      :template_name => find_template("contact", lang, contact.listing.board.venue)
    }

    config.merge!({:bcc => contact.listing.board.venue.email}) if contact.listing.board.venue.bcc
    mail(config)
  end
  
  def confirm(lang, listing, board_url)
    @listing = listing
    @board_url = board_url

    format = listing.board.venue.date_format || "%e.%b %Y"
    @date = listing.ridedate.strftime(format).strip
    
    config = { 
      :from => "#{listing.board.venue.fullname} <#{listing.board.venue.email}>",
      :to => "#{listing.name} <#{listing.email}>", 
      :subject => "Rideshare email confirmation" + (listing.board.venue.bcc ? " for #{listing.name} <#{listing.email}>" : ""),
      :template_name => find_template("confirm", lang, listing.board.venue)
    }

    config.merge!({:bcc => listing.board.venue.email}) if listing.board.venue.bcc
    mail(config)
  end

  def remind(lang, listing, board_url)
    @listing = listing
    @board_url = board_url

    config = { 
      :from => "#{listing.board.venue.fullname} <#{listing.board.venue.email}>",
      :to => "#{listing.name} <#{listing.email}>", 
      :subject => 'Rideshare removal code reminder',
      :template_name => find_template("remind", lang, listing.board.venue)
    }

    mail(config)
  end

  private

  def find_template(template_name, lang, venue)
    path = Rideboard::Application.root + 'app' + 'views' + 'mailer' + "#{template_name}*.erb"
    lvname = "#{template_name}.#{lang}.#{venue.name}"
    vname = "#{template_name}.#{venue.name}"
    lname = "#{template_name}.#{lang}"

    lvfile = nil
    vfile = nil
    lfile = nil
    Pathname.glob(path.to_s).map do |f|
      f = f.basename.to_s
      lvfile = f if f =~ /^#{lvname}/
      vfile = f if f =~ /^#{vname}/
      lfile = f if f =~ /^#{lname}/
    end
    lvfile || vfile || lfile || template_name
  end
end
