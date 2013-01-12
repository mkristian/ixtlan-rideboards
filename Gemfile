source 'https://rubygems.org'

RAILS_VERSION = '~> 3.2.11'
DM_VERSION    = '~> 1.2.0'

gem 'actionmailer',   RAILS_VERSION, :require => 'action_mailer'
gem 'railties',       RAILS_VERSION, :require => 'rails'
gem 'tzinfo',         '~> 0.3.32'

gem 'dm-rails',               '~> 1.2.1'

gem 'dm-migrations',   DM_VERSION
gem 'dm-types',        DM_VERSION
gem 'dm-validations',  DM_VERSION
gem 'dm-constraints',  DM_VERSION
gem 'dm-transactions', DM_VERSION
gem 'dm-aggregates',   DM_VERSION
gem 'dm-timestamps',   DM_VERSION
gem 'dm-observer',     DM_VERSION

group :test do
  # Pretty printed test output
  gem 'turn', '~> 0.9.4', :require => false
end

group :development do
  gem 'dm-sqlite-adapter', DM_VERSION
end

group :production do
  gem 'dm-postgres-adapter', DM_VERSION
end

gem "ixtlan-session-timeout"
gem "ixtlan-guard", '~> 0.9'#, :path => '../../ixtlan/ixtlan-guard'
gem "ixtlan-error-handler", "~> 0.3"
gem "ixtlan-audit", "~> 0.3"
gem 'ixtlan-babel', '~> 0.2'#, :path => '../../ixtlan/ixtlan-babel'
gem "ixtlan-optimistic", '~> 0.2.1'#, :path => '../../ixtlan/ixtlan-optimistic'
gem 'ixtlan-remote','~> 0.1'#, :path => '../../ixtlan/ixtlan-remote'
gem 'ixtlan-gettext','~> 0.1'#, :path => '../../ixtlan/ixtlan-gettext'

gem 'slf4r', '~> 0.4.2'
gem 'pony', '~> 1.4'
gem 'gettext_i18n_rails'
gem "enforce-ssl"

gem 'copyright-header', '~> 1.0.7', :group => :development
