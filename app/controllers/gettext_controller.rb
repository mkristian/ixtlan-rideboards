require 'ixtlan/gettext/controller'
class GettextController < ApplicationController

  skip_before_filter :authorize

  skip_before_filter :check_session

  include Ixtlan::Gettext::Controller

end
