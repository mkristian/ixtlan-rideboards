package org.dhamma.rideboards.client.views;

import org.dhamma.rideboards.client.models.Configuration;
import org.dhamma.rideboards.client.presenters.ConfigurationPresenter;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

@ImplementedBy(ConfigurationViewImpl.class)
public interface ConfigurationView extends IsWidget {

    void setPresenter(ConfigurationPresenter presenter);

    void show(Configuration model);

    void reload(Configuration model);

    void edit(Configuration model);

    boolean isDirty();
}
