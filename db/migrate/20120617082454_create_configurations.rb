class CreateConfigurations < ActiveRecord::Migration
  def self.up
    create_table :configurations do |t|
      t.integer :errors_keep_dumps
      t.string :errors_base_url
      t.string :errors_from_email
      t.string :errors_to_emails
      t.integer :idle_session_timeout
      t.integer :audits_keep_logs

      t.timestamps

      t.references :modified_by
    end
  end

  def self.down
    drop_table :configurations
  end
end
