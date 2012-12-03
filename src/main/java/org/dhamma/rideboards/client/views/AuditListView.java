package org.dhamma.rideboards.client.views;

import java.util.List;

import org.dhamma.rideboards.client.models.Audit;
import org.dhamma.rideboards.client.presenters.AuditPresenter;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

@ImplementedBy(AuditListViewImpl.class)
public interface AuditListView extends IsWidget {

    void setPresenter(AuditPresenter presenter);

    void reset(List<Audit> models);
}