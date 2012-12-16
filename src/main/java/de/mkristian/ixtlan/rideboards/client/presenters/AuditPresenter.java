package de.mkristian.ixtlan.rideboards.client.presenters;


import javax.inject.Inject;
import javax.inject.Singleton;

import de.mkristian.gwt.rails.presenters.ReadOnlyPresenterImpl;
import de.mkristian.ixtlan.rideboards.client.RideboardsErrorHandler;
import de.mkristian.ixtlan.rideboards.client.caches.AuditRemoteReadOnly;
import de.mkristian.ixtlan.rideboards.client.models.Audit;
import de.mkristian.ixtlan.rideboards.client.views.AuditListView;
import de.mkristian.ixtlan.rideboards.client.views.AuditView;

@Singleton
public class AuditPresenter extends ReadOnlyPresenterImpl<Audit> {

    @Inject
    public AuditPresenter( RideboardsErrorHandler errors, 
                AuditView view, 
                AuditListView listView, 
                AuditRemoteReadOnly remote ){
        super( errors, view, listView, remote );
    }
}
