class Public::ListingsController < Public::ApplicationController

  before_filter :setup, :cleanup_params

  private

  def cleanup_params
    filter.filter(params[:listing])
  end

  def listing
    @listing ||= @board.listings.get(params[:id])
    if @listing
      @listing
    else
      @listing = "- error: Listing(#{params[:id]}) no found"
      render :template => "public/nolisting"
      false
    end
  end

  public

  def index
    raise Exception.new("forced error to test error page")
  end

  # GET /listings/offer
  def offer
    @listing = @board.new_listing_offer

    render :template => "public/new"
  end

  # GET /listings/wanted
  def wanted
    @listing = @board.new_listing_request

    render :template => "public/new"
  end

  # GET /listings/1/edit
  def edit
    if listing
      render :template => "public/removal"
    end
  end

  # POST /listings/1/reminder
  def reminder
    if listing
      url = url_for(:id => listing.id,
                    :venue => @board.venue.name, 
                    :board => @board.name, 
                    :lang => @lang, 
                    :controller => 'public/listings', 
                    :action => :edit)
      listing.send_reminder(url, @lang)
      render :template => "public/reminder_sent"
    end
  end

  # POST /listings
  def create
    date = DateTime.civil(params[:listing].delete("ridedate(1i)").to_i, params[:listing].delete("ridedate(2i)").to_i, params[:listing].delete("ridedate(3i)").to_i)
    @listing = @board.create_listing(date, params[:listing])
     
    if @listing
      if @listing.valid?
        @listing.send_confirmation(@base_url, @lang)
        render :template => "public/created"
      else
        render :template => "public/new"
      end
    else
      # just create listing for log and view
      @listing = Listing.new(params[:listing])
      render :template => "public/exists"      
    end
  end

  # DELETE /listings/1
  def destroy
    @listing = @board.delete_listing(params[:id], params[:password])

    if @listing
      render :template => "public/confirmed"
    else
      @listing = @board.listings.get(params[:id])
      def @listing.to_log 
        "Listing(#{id}) - error: not authorized, wrong password"
      end
      render :template => "public/not_authorized"
    end
  end

  def new_contact
    if listing
      @contact = listing.new_contact

      render :template => "public/contact"
    end
  end

  def contact
    if listing
      @contact = listing.new_contact(params[:contact])
      if @message = @contact.send_message(@lang)
        render :template => "public/sent" 
      else
        render :template => "public/contact"
      end
    end
  end
end
