package org.dhamma.rideboards.client.views;

import org.dhamma.rideboards.client.models.Error;
import org.dhamma.rideboards.client.presenters.ErrorPresenter;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

@ImplementedBy(ErrorViewImpl.class)
public interface ErrorView extends IsWidget {

    void setPresenter(ErrorPresenter presenter);

    void show(Error model);

    boolean isDirty();
}
