package de.mkristian.ixtlan.rideboards.client.views;


import com.google.inject.ImplementedBy;

import de.mkristian.gwt.rails.views.SingletonView;
import de.mkristian.ixtlan.rideboards.client.models.Configuration;


@ImplementedBy(ConfigurationViewImpl.class)
public interface ConfigurationView extends SingletonView<Configuration>{
}
