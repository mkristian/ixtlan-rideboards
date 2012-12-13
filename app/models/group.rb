require 'ixtlan/user_management/group_model'
class Group < Ixtlan::UserManagement::Group

  attribute :domains, Array[Domain]

  def initialize(attributes = {})
    self.domains = setup( Domain, attributes.delete( 'domains' ) )
    super
  end

  def root?
    @is_root ||= name == 'root'
  end

  private

  def updater
    @updater ||= Updater.new
  end

  def setup( model, assos )
    assos ||= []
    ids = assos.collect { |a| a['id'].to_i }
    result = model.all.select { |r| ids.include? r.id }
    if result.size != assos.size
      updater.do_it model
      result = model.all.select { |r| ids.include? r.id }
    end
    result
  end
end
