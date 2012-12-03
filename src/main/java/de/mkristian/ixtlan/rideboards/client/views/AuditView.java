package de.mkristian.ixtlan.rideboards.client.views;


import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.rideboards.client.models.Audit;
import de.mkristian.ixtlan.rideboards.client.presenters.AuditPresenter;

@ImplementedBy(AuditViewImpl.class)
public interface AuditView extends IsWidget {

    void setPresenter(AuditPresenter presenter);

    void show(Audit model);

    boolean isDirty();
}
