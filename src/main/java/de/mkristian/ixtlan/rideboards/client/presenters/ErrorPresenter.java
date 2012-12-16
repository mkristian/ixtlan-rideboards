package de.mkristian.ixtlan.rideboards.client.presenters;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.mkristian.gwt.rails.presenters.ReadOnlyPresenterImpl;
import de.mkristian.ixtlan.rideboards.client.RideboardsErrorHandler;
import de.mkristian.ixtlan.rideboards.client.caches.ErrorRemoteReadOnly;
import de.mkristian.ixtlan.rideboards.client.models.Error;
import de.mkristian.ixtlan.rideboards.client.views.ErrorListView;
import de.mkristian.ixtlan.rideboards.client.views.ErrorView;

@Singleton
public class ErrorPresenter extends ReadOnlyPresenterImpl<Error> {

    @Inject
    public ErrorPresenter( RideboardsErrorHandler errors, 
                ErrorView view, 
                ErrorListView listView, 
                ErrorRemoteReadOnly remote ){
        super( errors, view, listView, remote );
    }
}
