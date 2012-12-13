require 'date'

# use UTC everywhere
# keep default format to be database compatible, i.e. no millis or nanos

class DateTime

  def self.now
    Time.now.to_datetime.utc
  end

  def to_s(arg = nil)
    if arg && ! arg.is_a?(Symbol)
      super
    elsif utc?
      strftime('%Y-%m-%dT%H:%M:%S')
    else
      utc.to_s
    end
  end

  def self.parse( str )
    Time.parse( str ).to_datetime.utc
  end
end
