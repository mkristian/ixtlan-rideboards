#-*- mode: ruby -*-\n# Add your own tasks in files placed in lib/tasks ending in .rake,
# for example lib/tasks/capistrano.rake, and they will automatically be available to Rake.

require File.expand_path('../config/application', __FILE__)
require 'rake'

Rideboards::Application.load_tasks

desc 'update all configured remote resources'
task :update => [:environment] do
    sync = Updater.new
    sync.do_it

    puts "#{Time.now.strftime('%Y-%m-%d %H:%M:%S')}\n\t#{sync}"
end

namespace :update do

  desc 'update remote resources from gettext server'
  task :gettext => [:environment] do
    sync = Updater.new
    sync.do_it( Rideboards::Application.config.rest.server( :gettext ).models )

    puts "#{Time.now.strftime('%Y-%m-%d %H:%M:%S')}\n\t#{sync}"
  end

  desc 'update remote resources from users server'
  task :users => [:environment] do
    sync = Updater.new
    sync.do_it( Rideboards::Application.config.rest.server( :users ).models )

    puts "#{Time.now.strftime('%Y-%m-%d %H:%M:%S')}\n\t#{sync}"
  end

end
# vim: syntax=Ruby
