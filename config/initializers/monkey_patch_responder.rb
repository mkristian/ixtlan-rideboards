module ActionController
  class Responder
    def empty_json_resource
      resource.to_json
    end
    def empty_xml_resource
      resource.to_xml
    end
    def empty_yml_resource
      resource.to_yml
    end
  end
end
