class ConfigurationsController < ApplicationController

  private

  def cleanup
    filter.filter(params[:configuration])
  end

  public

  # GET /configurations
  def show
    @configuration = serializer(::Configuration.instance)
    respond_with @configuration
  end

  # PUT /configurations/1
  def update
    @configuration = serializer(::Configuration.optimistic_get!(updated_at, ::Configuration.instance.id))
    @configuration.attributes = filter.params

    @configuration.save

    respond_with @configuration
  end
end
