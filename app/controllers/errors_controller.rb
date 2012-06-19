class ErrorSerializer < Babel::NoTimestampSerializer
  add_defaults('error')
end

class ErrorsController < ApplicationController

  public

  # GET /errors
  # GET /errors.xml
  # GET /errors.json
  def index
    @errors = serializer(Error.all).use(:collection)

    respond_with(@errors)
  end

  # GET /errors/1
  # GET /errors/1.xml
  # GET /errors/1.json
  def show
    @error = serializer(Error.get(params[:id]))

    respond_with(@error)
  end
end
