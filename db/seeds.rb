# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ :name => 'Chicago' }, { :name => 'Copenhagen' }])
#   Mayor.create(:name => 'Daley', :city => cities.first)
unless u = User.first
  u = User.new(:name => "System", :login => "system", :updated_at => DateTime.now)
  u.id = 1
  u.save
end

c = Configuration.instance
if c.new?
  if defined? Ixtlan::Errors
    c.errors_keep_dumps = 30
  end
  if defined? Ixtlan::Audit
    c.audits_keep_logs = 90
  end
  if defined? Ixtlan::Sessions
    c.idle_session_timeout = 15
  end
  c.modified_by = u
  c.created_at = DateTime.now
  c.updated_at = c.created_at
  c.save!
end
ct = Center.first || Center.create(:name => 'test')
v = Venue.first || Venue.create(:fullname => 'Dhamma Test',
                                :email => 'rides@test.dhamma.org',
                                :enabled => true,
                                :center => ct,
                                :modified_by => u)
en = Locale.first || Locale.create(:code => 'en')
VenueConfig.first || VenueConfig.create(:venue => v,
                                        :home_url => 'http://www.test.dhamma.org',
                                        :schedule_url => 'http://www.test.dhamma.org/schedule',
                                        :locale => en,
                                        :modified_by => u)
b = Board.first || Board.create(:name => 'center',
                                :fullname => 'Main Center',
                                :position => 1,
                                :enabled => true,
                                :modified_by => u,
                                :venue => v)
BoardConfig.first || BoardConfig.create(:locale => en,
                                       :board => b,
                                       :modified_by => u)
