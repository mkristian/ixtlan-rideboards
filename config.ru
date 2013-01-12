# -*- mode: ruby -*-
# This file is used by Rack-based servers to start the application.

require ::File.expand_path('../config/environment',  __FILE__)
require 'cuba_api'

CubaAPI.define do

  on "bla" do
    res.write "bla"
  end

  on default do
    run Rideboards::Application
  end
end

run CubaAPI

# vim: syntax=Ruby
