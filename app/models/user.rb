require 'ixtlan/user_management/user_resource'
class Ixtlan::UserManagement::User

  # use the same table as User
  def self.storage_name(repo = :default)
    'users'
  end
end
class User < Ixtlan::UserManagement::User
end
