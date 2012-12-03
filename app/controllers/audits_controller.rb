class AuditsController < ApplicationController

  public

  # GET /audits
  def index
    @audits = serializer(Audit.all).use(:collection)
    respond_with @audits
  end

  # GET /audits/1
  def show
    @audit = serializer(Audit.get!(params[:id]))
    respond_with @audit
  end
end
