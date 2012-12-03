package de.mkristian.ixtlan.rideboards.client.views;

import java.util.List;


import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.rideboards.client.models.Audit;
import de.mkristian.ixtlan.rideboards.client.presenters.AuditPresenter;

@ImplementedBy(AuditListViewImpl.class)
public interface AuditListView extends IsWidget {

    void setPresenter(AuditPresenter presenter);

    void reset(List<Audit> models);
}