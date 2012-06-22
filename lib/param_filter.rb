require 'babel/factory'
require 'babel/hash_filter'

class ParamFilter < Babel::HashFilter
  
  attr_reader :updated_at, :params
  
  def initialize
    super({:except => [:id, :created_at, :updated_at, :modified_by_id]})
  end
  
  def filter(params)
    if params
      @updated_at = params[:updated_at]
      @params = super
    end
  end
end
