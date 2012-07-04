class AuditSerializer < Ixtlan::Babel::NoTimestampSerializer
  add_defaults('audit')
end

class AuditsController < ApplicationController

  public

  # GET /audits
  # GET /audits.xml
  # GET /audits.json
  def index
    @audits = serializer(Audit.all).use(:collection)

    respond_with(@audits)
  end

  # GET /audits/1
  # GET /audits/1.xml
  # GET /audits/1.json
  def show
    @audit = serializer(Audit.get(params[:id]))

    respond_with(@audit)
  end
end
