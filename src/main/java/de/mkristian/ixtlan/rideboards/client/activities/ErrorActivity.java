package de.mkristian.ixtlan.rideboards.client.activities;


import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import de.mkristian.gwt.rails.activities.AbstractReadOnlyActivity;
import de.mkristian.gwt.rails.events.ModelEventHandler;
import de.mkristian.ixtlan.rideboards.client.events.ErrorEvent;
import de.mkristian.ixtlan.rideboards.client.models.Error;
import de.mkristian.ixtlan.rideboards.client.places.ErrorPlace;
import de.mkristian.ixtlan.rideboards.client.presenters.ErrorPresenter;

public class ErrorActivity extends AbstractReadOnlyActivity<Error> {

    @Inject
    public ErrorActivity( @Assisted ErrorPlace place,
                ErrorPresenter presenter) {
        super( place, presenter );
    }

    @Override
    protected Type<ModelEventHandler<Error>> eventType() {
        return ErrorEvent.TYPE;
    }
}
