package de.mkristian.ixtlan.rideboards.client.views;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.rideboards.client.models.Audit;

@ImplementedBy(AuditListViewImpl.class)
public interface AuditListView extends IsWidget {

    void reset(List<Audit> models);
}