package de.mkristian.ixtlan.rideboards.client.views;


import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.rideboards.client.models.Error;
import de.mkristian.ixtlan.rideboards.client.presenters.ErrorPresenter;

@ImplementedBy(ErrorViewImpl.class)
public interface ErrorView extends IsWidget {

    void setPresenter(ErrorPresenter presenter);

    void show(Error model);

    boolean isDirty();
}
