require 'ixtlan/babel/serializer'

class AuditSerializer < Ixtlan::Babel::Serializer

  model Audit

  root 'audit'

  add_context(:single)

  add_context(:collection,
              :except => [:created_at]
             )
end
