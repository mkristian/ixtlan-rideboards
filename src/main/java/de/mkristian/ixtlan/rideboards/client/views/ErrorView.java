package de.mkristian.ixtlan.rideboards.client.views;


import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.rideboards.client.models.Error;

@ImplementedBy(ErrorViewImpl.class)
public interface ErrorView extends IsWidget {

    void show(Error model);
}
