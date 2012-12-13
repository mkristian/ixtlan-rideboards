package de.mkristian.ixtlan.rideboards.client.views;


import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.rideboards.client.models.Configuration;
import de.mkristian.ixtlan.rideboards.client.presenters.ConfigurationPresenter;

@ImplementedBy(ConfigurationViewImpl.class)
public interface ConfigurationView extends IsWidget {

    void setPresenter(ConfigurationPresenter presenter);

    void show(Configuration model);

    //void reload(Configuration model);

    void edit(Configuration model);

    boolean isDirty();
}
