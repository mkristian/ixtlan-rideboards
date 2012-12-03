class ErrorsController < ApplicationController

  public

  # GET /errors
  def index
    @errors = serializer(Error.all).use(:collection)
    respond_with @errors
  end

  # GET /errors/1
  def show
    @error = serializer(Error.get!(params[:id]))
    respond_with @error
  end
end
