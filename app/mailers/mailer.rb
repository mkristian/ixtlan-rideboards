class Mailer

  def self.send( config, template_name, args = {} )
    template = File.read( File.expand_path( template_name + '.text.erb', 
                                           File.dirname( __FILE__ ) ) )
p args
    config[ :body ] = Erubis::Eruby.new( template ).result( args )
    Pony.mail config
    config[ :body ]
  end
  private :send

  def self.contact( contact )
    config = { 
      :from => "#{contact.listing.board.venue.fullname} <#{contact.listing.board.venue.email}>",
      :to => "#{contact.listing.name} <#{contact.listing.email}>", 
      :subject => "Rideshare contact information" + ( contact.listing.board.venue.bcc ? " for #{contact.listing.name} <#{contact.listing.email}>" : "" ),
    }
    config[ :bcc] = contact.listing.board.venue.email if contact.listing.board.venue.bcc

    send( config, 'contact', :contact => contact )
  end
  
  def self.confirm( listing, board_url )
    #@listing = listing
    #@board_url = board_url

    #@date = listing.ridedate.strftime(format).strip
    
    config = { 
      :from => "#{listing.board.venue.fullname} <#{listing.board.venue.email}>",
      :to => "#{listing.name} <#{listing.email}>", 
      :subject => "Rideshare email confirmation" + ( listing.board.venue.bcc ? " for #{listing.name} <#{listing.email}>" : "" ),
    }

    config[ :bcc ] = listing.board.venue.email if listing.board.venue.bcc

    format = listing.board.venue.date_format || "%e.%b %Y"
    send( config, 'confirm', :listing => listing, :board_url => board_url,
          :date => listing.ridedate.strftime( format ).strip )
  end

  def self.remind( listing, board_url )
#    @listing = listing
#    @board_url = board_url

    config = { 
      :from => "#{listing.board.venue.fullname} <#{listing.board.venue.email}>",
      :to => "#{listing.name} <#{listing.email}>", 
      :subject => 'Rideshare removal code reminder',
    }

    send( config, 'remind', :listing => listing, :board_url => board_url )
  end
end
