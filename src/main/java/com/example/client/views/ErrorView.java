package com.example.client.views;

import java.util.List;

import com.example.client.models.Error;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.gwt.rails.places.RestfulAction;

@ImplementedBy(ErrorViewImpl.class)
public interface ErrorView extends IsWidget {

    public interface Presenter {
        void goTo(Place place);
    }

    void setup(Presenter presenter, RestfulAction action);

    void reset(List<Error> models);

    void edit(Error model);
}