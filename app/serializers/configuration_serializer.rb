require 'babel/serializer'
class ConfigurationSerializer < Babel::Serializer

  add_context(:single,
              :root => 'configuration',
              :except => [:modified_by_id],
              :include => {
                :modified_by => {
                  :only => [:id, :login, :name]
                }
              }
           )

  default_context_key :single
end
