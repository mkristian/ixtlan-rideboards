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
  task :translations => [:environment] do
    sync = Updater.new
    sync.do_it( Rideboards::Application.config.rest.server( :translations ).models )

    puts "#{Time.now.strftime('%Y-%m-%d %H:%M:%S')}\n\t#{sync}"
  end

  desc 'update remote resources from users server'
  task :users => [:environment] do
    sync = Updater.new
    sync.do_it( Rideboards::Application.config.rest.server( :users ).models )

    puts "#{Time.now.strftime('%Y-%m-%d %H:%M:%S')}\n\t#{sync}"
  end

end

task :headers do
  require 'rubygems'
  require 'copyright_header'

  args = {
    :license => 'AGPL3', 
    :copyright_software => 'ixtlan_rideboards',
    :copyright_software_description => 'webapp to manage car pools to reach configured locations',
    :copyright_holder => ['Christian Meier'],
    :copyright_years => [Time.now.year],
    :add_path => ['lib', 'app', 'src', 'config', 'db/seeds.rb'].join(File::SEPARATOR),
    :output_dir => '.'
  }

  command_line = CopyrightHeader::CommandLine.new( args )
  command_line.execute
end

# vim: syntax=Ruby
