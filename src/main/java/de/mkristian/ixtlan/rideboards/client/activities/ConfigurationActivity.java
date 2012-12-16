package de.mkristian.ixtlan.rideboards.client.activities;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import de.mkristian.gwt.rails.activities.AbstractSingletonActivity;
import de.mkristian.gwt.rails.events.ModelEventHandler;
import de.mkristian.gwt.rails.places.RestfulActionEnum;
import de.mkristian.gwt.rails.places.RestfulPlace;
import de.mkristian.ixtlan.rideboards.client.events.ConfigurationEvent;
import de.mkristian.ixtlan.rideboards.client.models.Configuration;
import de.mkristian.ixtlan.rideboards.client.places.ConfigurationPlace;
import de.mkristian.ixtlan.rideboards.client.presenters.ConfigurationPresenter;

public class ConfigurationActivity extends AbstractSingletonActivity<Configuration> {

    @Inject
    public ConfigurationActivity( @Assisted ConfigurationPlace place,
                ConfigurationPresenter presenter,
                PlaceController places ) {
        super( place, presenter, places );
    }

    protected Type<ModelEventHandler<Configuration>> eventType() {
        return ConfigurationEvent.TYPE;
    }

    @Override
    protected RestfulPlace<Configuration, ?> showPlace(Configuration model) {
        return new ConfigurationPlace( model, RestfulActionEnum.SHOW );
    }
}
