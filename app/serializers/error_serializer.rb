require 'ixtlan/babel/serializer'

class ErrorSerializer < Ixtlan::Babel::Serializer

  model Error

  add_context(:single,
              :root => 'error'
             )

  add_context(:collection,
              :root => 'error',
              :except => [:created_at]
             )

  default_context_key :single
end
