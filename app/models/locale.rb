require 'ixtlan/gettext/locale_resource'
class Ixtlan::Gettext::Locale

  # use the same table as Locale
  def self.storage_name(repo = :default)
    'locales'
  end
end

class Locale < Ixtlan::Gettext::Locale
end

