class Permissions

  def self.for(groups)
    Rails.configuration.guard.permissions( groups ) do |r,a|
      if r == 'applications' # TODO which resources
        d = a.collect { |g| g.domains }
        d.flatten!
        d.uniq!;
        d = [Domain.new(:name => '')] if d.empty?
        d
      end
    end
  end
end
