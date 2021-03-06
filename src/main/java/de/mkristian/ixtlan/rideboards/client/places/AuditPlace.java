package de.mkristian.ixtlan.rideboards.client.places;

import de.mkristian.gwt.rails.places.RestfulAction;
import de.mkristian.gwt.rails.places.RestfulPlace;
import de.mkristian.ixtlan.rideboards.client.managed.ActivityFactory;
import de.mkristian.ixtlan.rideboards.client.models.Audit;


import com.google.gwt.activity.shared.Activity;

public class AuditPlace extends RestfulPlace<Audit, ActivityFactory> {
    
    public static final String NAME = "audits";

    public Activity create(ActivityFactory factory){
        return factory.create(this);
    }
    
    public AuditPlace(RestfulAction restfulAction) {
        super(restfulAction, NAME);
    }

    public AuditPlace(Audit model, RestfulAction restfulAction) {
        super(model.getId(), model, restfulAction, NAME);
    }

    public AuditPlace(int id, RestfulAction restfulAction) {
        super(id, restfulAction, NAME);
    }    
    
    public AuditPlace(String id, RestfulAction restfulAction) {
        super(id, restfulAction, NAME);
    }
}