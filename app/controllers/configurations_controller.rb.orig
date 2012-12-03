class ConfigurationsController < ApplicationController

  before_filter :cleanup_params

  private

  def cleanup_params
    filter.filter(params[:configuration])
  end

  public

  # GET /configurations
  # GET /configurations.xml
  # GET /configurations.json
  def show
    @configuration = serializer(::Configuration.instance)

    respond_with(@configuration)
  end

  # PUT /configurations
  # PUT /configurations.xml
  # PUT /configurations.json
  def update
    if @configuration = serializer(::Configuration.optimistic_get(filter.updated_at, 
                                                                  ::Configuration.instance.id))

      @configuration.attributes = filter.params
      @configuration.modified_by = current_user if @configuration.dirty?
      
      @configuration.save
      
      respond_with(@configuration)
    else
      respond_with(nil, :status => :conflict)
    end
  end
end
