package de.mkristian.ixtlan.rideboards.client.places;

import de.mkristian.gwt.rails.places.RestfulAction;
import de.mkristian.gwt.rails.places.RestfulPlace;
import de.mkristian.ixtlan.rideboards.client.managed.ActivityFactory;
import de.mkristian.ixtlan.rideboards.client.models.Error;


import com.google.gwt.activity.shared.Activity;

public class ErrorPlace extends RestfulPlace<Error, ActivityFactory> {
    
    public static final String NAME = "errors";

    public Activity create(ActivityFactory factory){
        return factory.create(this);
    }
    
    public ErrorPlace(RestfulAction restfulAction) {
        super(restfulAction, NAME);
    }

    public ErrorPlace(Error model, RestfulAction restfulAction) {
        super(model.getId(), model, restfulAction, NAME);
    }

    public ErrorPlace(int id, RestfulAction restfulAction) {
        super(id, restfulAction, NAME);
    }    
    
    public ErrorPlace(String id, RestfulAction restfulAction) {
        super(id, restfulAction, NAME);
    }
}