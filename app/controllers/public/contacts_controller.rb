class Public::ContactsController < Public::ApplicationController
  
  before_filter :setup

  private

  def listing
    Listing.first!(:id => params[:listing_id], :board => @board)
  end

  public  

  def new
    @contact = listing.new_contact

    render :template => "public/contact"
  end

  def create
    @contact = listing.new_contact(params[:contact])
    if @message = @contact.send_message
      render :template => "public/sent" 
    else
      render :template => "public/contact"
    end
  end
end
