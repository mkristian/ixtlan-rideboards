require 'ixtlan/babel/serializer'

class ErrorSerializer < Ixtlan::Babel::Serializer

  model Error

  root 'error'

  add_context(:single )

  add_context(:collection,
              :except => [:created_at]
             )
end
