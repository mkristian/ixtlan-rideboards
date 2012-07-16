require 'stringio'
class Paragraph

  def self.format(text, size = 80)
    max = size + size/10
    result = StringIO.new
    lsize = 0
    text.split(/\s+/).each do |part|
      if (lsize + part.size) < size
        result << ' '
      elsif lsize < size && (size - lsize > max - lsize - part.size)
        result << ' '
      else
        lsize = 0
        result << "\n"
      end
      lsize += part.size
      result << part
    end
    result.string.strip
  end

end
