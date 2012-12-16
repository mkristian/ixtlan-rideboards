package de.mkristian.ixtlan.rideboards.client.activities;


import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import de.mkristian.gwt.rails.activities.AbstractReadOnlyActivity;
import de.mkristian.gwt.rails.events.ModelEventHandler;
import de.mkristian.ixtlan.rideboards.client.events.AuditEvent;
import de.mkristian.ixtlan.rideboards.client.models.Audit;
import de.mkristian.ixtlan.rideboards.client.places.AuditPlace;
import de.mkristian.ixtlan.rideboards.client.presenters.AuditPresenter;

public class AuditActivity extends AbstractReadOnlyActivity<Audit> {

    @Inject
    public AuditActivity( @Assisted AuditPlace place, 
                AuditPresenter presenter ) {
        super( place, presenter );
    }

    protected Type<ModelEventHandler<Audit>> eventType() {
        return AuditEvent.TYPE;
    }
}
