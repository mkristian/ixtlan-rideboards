require 'ixtlan/babel/serializer'

class AuditSerializer < Ixtlan::Babel::Serializer

  model Audit

  add_context(:single,
              :root => 'audit'
             )

  add_context(:collection,
              :root => 'audit',
              :except => [:created_at]
             )

  default_context_key :single
end
