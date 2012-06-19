#require 'rails/data_mapper/middleware/identity_map'
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

class ApplicationController < ActionController::Base

#  use Rails::DataMapper::Middleware::IdentityMap

  respond_to :json

  protect_from_forgery

  protected

  def filter
    @_filter ||= ParamFilter.new
  end

  def serializer(resource)
    if resource
      @_factory ||= Babel::Factory.new
      @_factory.new(resource)
    end
  end

  def current_user(user = nil)
    if user
      session['user'] = {'id' => user.id, 'groups' => user.groups}
      @_current_user = user
    else
      @_current_user ||= begin
                           data = session['user']
                           if data
                             u = User.get(data['id'])
                             u.groups = data['groups']
                             u
                           end
                         end            
    end
  end

  private

  after_filter :csrf

  def csrf
    response.header['X-CSRF-Token'] = form_authenticity_token if current_user
  end
end
