package de.mkristian.ixtlan.rideboards.client.views;


import com.google.inject.ImplementedBy;

import de.mkristian.gwt.rails.views.ReadOnlyListView;
import de.mkristian.ixtlan.rideboards.client.models.Audit;

@ImplementedBy(AuditListViewImpl.class)
public interface AuditListView extends ReadOnlyListView<Audit> {
}