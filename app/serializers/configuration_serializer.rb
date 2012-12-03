require 'ixtlan/babel/serializer'

class ConfigurationSerializer < Ixtlan::Babel::Serializer

  model Configuration

  add_context(:single,
              :root => 'configuration',
              :except => [:id, :modified_by_id],
              :include => {
                :modified_by => {
                  :only => [:id, :login, :name]
                }
              }
             )

  default_context_key :single
end
