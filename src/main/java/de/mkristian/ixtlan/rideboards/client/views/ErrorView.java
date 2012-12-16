package de.mkristian.ixtlan.rideboards.client.views;


import com.google.inject.ImplementedBy;

import de.mkristian.gwt.rails.views.ReadOnlyView;
import de.mkristian.ixtlan.rideboards.client.models.Error;

@ImplementedBy(ErrorViewImpl.class)
public interface ErrorView extends ReadOnlyView<Error> {
}
