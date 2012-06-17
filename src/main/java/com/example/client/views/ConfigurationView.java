package com.example.client.views;

import com.example.client.models.Configuration;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.gwt.rails.places.RestfulAction;

@ImplementedBy(ConfigurationViewImpl.class)
public interface ConfigurationView extends IsWidget {

    public interface Presenter {
        void save();
        void goTo(Place place);
    }

    void setup(Presenter presenter, RestfulAction action);

    void edit(Configuration model);

    Configuration flush();
}