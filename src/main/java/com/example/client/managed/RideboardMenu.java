package com.example.client.managed;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;

import static de.mkristian.gwt.rails.places.RestfulActionEnum.*;

import de.mkristian.gwt.rails.views.Menu;

@Singleton
public class RideboardMenu extends Menu {

    @Inject
    RideboardMenu(final PlaceController placeController){
        super(placeController);
    }
}
