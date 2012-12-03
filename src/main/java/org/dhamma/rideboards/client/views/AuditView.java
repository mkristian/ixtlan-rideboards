package org.dhamma.rideboards.client.views;

import org.dhamma.rideboards.client.models.Audit;
import org.dhamma.rideboards.client.presenters.AuditPresenter;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

@ImplementedBy(AuditViewImpl.class)
public interface AuditView extends IsWidget {

    void setPresenter(AuditPresenter presenter);

    void show(Audit model);

    boolean isDirty();
}
