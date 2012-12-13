package de.mkristian.ixtlan.rideboards.client.views;


import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.rideboards.client.models.Audit;

@ImplementedBy(AuditViewImpl.class)
public interface AuditView extends IsWidget {

    void show(Audit model);
}
