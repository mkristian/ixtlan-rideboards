package de.mkristian.ixtlan.rideboards.client.places;

import de.mkristian.gwt.rails.places.RestfulAction;
import de.mkristian.gwt.rails.places.RestfulPlace;
import de.mkristian.ixtlan.rideboards.client.managed.ActivityFactory;
import de.mkristian.ixtlan.rideboards.client.models.Configuration;


import com.google.gwt.activity.shared.Activity;

public class ConfigurationPlace extends RestfulPlace<Configuration, ActivityFactory> {
    
    public static final String NAME = "configurations";

    public Activity create(ActivityFactory factory){
        return factory.create(this);
    }
    
    public ConfigurationPlace(RestfulAction restfulAction) {
        super(restfulAction, NAME);
    }

    public ConfigurationPlace(Configuration model, RestfulAction restfulAction) {
        super(model, restfulAction, NAME);
    }

    public ConfigurationPlace(int id, RestfulAction restfulAction) {
        super(id, restfulAction, NAME);
    }    
    
    public ConfigurationPlace(String id, RestfulAction restfulAction) {
        super(id, restfulAction, NAME);
    }
}