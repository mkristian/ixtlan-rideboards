require 'yaml'
class NewLocale

  def initialize(lang)
    @lang = lang
    @file = "config/locales/#{lang}.yml"
    @trans = if File.exists? @file
               YAML.load_file(@file)["en"]
             else
               {}
             end
  end

  def crawl
    Dir[File.join('app', 'views', '**/*rb')].each do |f|
      File.read(f).each do |line|
        extract_key(line) if line =~ /_[ (]/
      end
    end
  end

  def dump
    merge
    result = {}
    result[@lang] = @trans
    lines = result.to_yaml.sub(/---\s*\n/, '').gsub(/  -\s*\n/, '').split /\n/
    lines.sort!{|n,m| n.sub(/^  /, "zz") <=> m.sub(/^  /, "zz") }
    File.open(@file, 'w') do |f|
      lines.each do |l|
        f.puts l
        f.puts "  -" if l =~ /^  .*: $/
      end
    end
  end

  def self.doit(lang)
    l = new(lang)
    l.crawl
    l.dump
  end

  private

  def merge
    keys.each do |k|
      @trans[k] = k unless @trans.key? k
    end
    @trans.keys.each do |k|
      @trans[" Deleted-Key-#{k}"] = @trans.delete(k) if !keys.member?(k) && (k =~ /^ Deleted-Key-/).nil?
    end
  end

  def keys
    @keys ||= []
  end

  def extract_key(line)
    line.gsub!(/_[^ (]/, '-')
    extract(line, "'")
    extract(line, '"')
  end

  def extract(line, sep)
    if line =~ /.*_[ (][#{sep}]/
      k = line.sub(/[^_]*_[ (][#{sep}]/, '').sub(/[#{sep}].*\n/, '')
      keys << k; puts k unless keys.member? k
      extract_key(line.sub(/[^_]*_[ (][#{sep}][^#{sep}]+[#{sep}]/, ''))
    end
  end
end
