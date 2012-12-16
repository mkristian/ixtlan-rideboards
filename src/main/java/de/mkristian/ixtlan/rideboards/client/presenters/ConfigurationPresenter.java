package de.mkristian.ixtlan.rideboards.client.presenters;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.mkristian.gwt.rails.presenters.SingletonPresenterImpl;
import de.mkristian.ixtlan.rideboards.client.RideboardsErrorHandler;
import de.mkristian.ixtlan.rideboards.client.caches.ConfigurationRemoteSingleton;
import de.mkristian.ixtlan.rideboards.client.models.Configuration;
import de.mkristian.ixtlan.rideboards.client.views.ConfigurationView;

@Singleton
public class ConfigurationPresenter 
            extends SingletonPresenterImpl<Configuration> {

    @Inject
    public ConfigurationPresenter( RideboardsErrorHandler errors,
                ConfigurationView view,
                ConfigurationRemoteSingleton remote ){
        super( errors, view, remote );
    }
}
