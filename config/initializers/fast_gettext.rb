FastGettext.add_text_domain 'app', :path => 'config/locales', :type => :yaml
FastGettext.add_text_domain 'dvara', :path => 'config/locales/dvara', :type => :yaml
FastGettext.default_available_locales = ['en','de', 'en_US'] #all you want to allow
FastGettext.default_text_domain = 'app'
