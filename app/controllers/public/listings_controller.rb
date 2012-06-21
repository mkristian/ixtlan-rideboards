class Public::ListingsController < Public::ApplicationController

  before_filter :setup

  public

  def index
    raise Exception.new("forced error to test error page")
  end

  # GET /listings/offer
  def offer
    @listing = Listing.new
    @listing.driver = true
    @listing.board = @board

    render :template => "public/new"
  end

  # GET /listings/wanted
  def wanted
    @listing = Listing.new
    @listing.driver = false
    @listing.board = @board

    render :template => "public/new"
  end

  # GET /listings/1/edit
  def edit
    @listing = Listing.first(:id => params[:id], :board => @board)

    if @listing
      render :template => "public/removal"
    else
      @listing = "- error: Listing(#{params[:id]}) no found"
      render :template => "public/nolisting"
    end
  end

  # POST /listings/1/reminder
  def reminder
    @listing = Listing.first!(:id => params[:id], :board => @board)

    # @listing.board.venue has no lang_code state set !!!
    url = @venue.iframe_url
    if url.blank?
      if @venue.password
        # TODO maybe it is just the other url as well ?!
        url = url_for(:controller => :listings, 
                      :action => :edit) 
      else
        url = url_for(:id => @listing.id,
                      :venue => @venue.name, 
                      :board => @board.name, 
                      :lang => @lang, 
                      :controller => 'public/listings', 
                      :action => :edit)
      end
    end
    logger.debug "listing url: " + url if logger && logger.debug?
    Mailer.deliver_remind(@listing, url)
    render :template => "public/reminder_sent"
  end

  # POST /listings
  def create
    date = DateTime.civil(params[:listing].delete("ridedate(1i)").to_i, params[:listing].delete("ridedate(2i)").to_i, params[:listing].delete("ridedate(3i)").to_i)
    @listing = Listing.new(params[:listing])
    @listing.ridedate = date
    @listing.board = @board
    @listing.reset_password
     
    if !Listing.first(:board_id => @listing.board.id, 
                      :name => @listing.name, 
                      :location => @listing.location, 
                      :email => @listing.email, 
                      :ridedate => @listing.ridedate, 
                      :driver => @listing.driver).nil?
      render :template => "public/exists"      
    elsif @listing.save
      url = @venue.iframe_url
      if url.blank? 
        url = url_for(:venue => @venue.name, 
                      :id => @board.id, 
                      :lang => @lang, 
                      :controller => :boards, 
                      :action => :show)
      end
      
      Mailer.deliver_confirm(@listing, url, @lang)

      render :template => "public/created"
    else
      render :template => "public/new"
    end
  end

  # DELETE /listings/1
  def destroy
    @listing = Listing.first(:id => params[:id], :board => @board)

    if @listing && params[:password] == @listing.password
      @listing.destroy
      render :template => "public/confirmed"
    else
      render :template => "public/not_authorized"
    end
  end

end
