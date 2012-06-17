#-*- mode: ruby -*-\n# Add your own tasks in files placed in lib/tasks ending in .rake,
# for example lib/tasks/capistrano.rake, and they will automatically be available to Rake.

require File.expand_path('../config/application', __FILE__)
require 'rake'

Rideboard::Application.load_tasks

desc 'triggers the heartbeat request (user updates)'
task :heartbeat => [:environment] do
    heartbeat = Heartbeat.new
    heartbeat.beat

    puts "#{DateTime.now.strftime('%Y-%m-%d %H:%M:%S')} - #{heartbeat}"
end
# vim: syntax=Ruby
