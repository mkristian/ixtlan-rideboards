package com.example.client.managed;

import static de.mkristian.gwt.rails.places.RestfulActionEnum.INDEX;
import static de.mkristian.gwt.rails.places.RestfulActionEnum.SHOW;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.place.shared.PlaceController;

import de.mkristian.gwt.rails.session.Guard;
import de.mkristian.gwt.rails.views.SessionMenu;

@Singleton
public class RideboardMenuPanel extends SessionMenu {

    @Inject
    RideboardMenuPanel(final PlaceController placeController, Guard guard){
        super(placeController, guard);
        addButton("Configuration", new com.example.client.places.ConfigurationPlace(SHOW));
        addButton("Errors", new com.example.client.places.ErrorPlace(INDEX));
        addButton("Audits", new com.example.client.places.AuditPlace(INDEX));
    }
}
