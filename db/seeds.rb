unless u = User.first
  u = User.new( :name => "System", :login => "system", :updated_at => DateTime.new( 0 ) )
  u.id = 1
  u.save
  p u if u.valid?
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
  p c if c.valid?
end

d = Domain.first
unless d
  d = Domain.create( :name => 'test', :updated_at => DateTime.new( 0 ) )
  p d if d.valid?
end

v = Venue.first
unless v
  v = Venue.create(:fullname => 'Dhamma Test',
                                :email => 'rides@test.dhamma.org',
                                :enabled => true,
                                :domain => d,
                                :modified_by => u)
  p v if v.valid?
end

unless en = Locale.get( 1 )
  en = Locale.create( :code => 'en', :updated_at => DateTime.new( 0 ) )
  p en if en.valid?
end

vc = VenueConfig.first 
unless vc
  vc = VenueConfig.create(:venue => v,
                          :home_url => 'http://www.test.dhamma.org',
                          :schedule_url => 'http://www.test.dhamma.org/schedule',
                          :locale => en,
                          :modified_by => u)
  p vc if vc.valid?
end

b = Board.first
unless b
  b = Board.create(:name => 'center',
                   :fullname => 'Main Center',
                   :position => 1,
                   :enabled => true,
                   :modified_by => u,
                   :venue => v)
  p b if b.valid?
end

bc = BoardConfig.first 
unless bc
  bc = BoardConfig.create(:locale => en,
                          :board => b,
                          :modified_by => u)
  p bc if bc.valid?
end

puts 'seeding done'
