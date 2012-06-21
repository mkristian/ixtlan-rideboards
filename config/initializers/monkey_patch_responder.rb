module ActionController
  class Responder
    # def empty_json_resource
    #   resource.to_json
    # end
    # def empty_xml_resource
    #   resource.to_xml
    # end
    # def empty_yml_resource
    #   resource.to_yml
    # end

    def api_behavior(error)
      raise error unless resourceful?

      if get? || put?
        display resource
      elsif post?
        display resource, :status => :created, :location => api_location
      else
        head :no_content
      end
    end
  end
end
