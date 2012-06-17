package com.example.client.managed;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.example.client.models.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;

import static de.mkristian.gwt.rails.places.RestfulActionEnum.*;
import de.mkristian.gwt.rails.session.SessionManager;
import de.mkristian.gwt.rails.views.MenuPanel;

@Singleton
public class RideboardMenuPanel extends MenuPanel<User> {

    @Inject
    RideboardMenuPanel(final PlaceController placeController, SessionManager<User> sessionManager){
        super(sessionManager, placeController);
        addButton("Configuration", new com.example.client.places.ConfigurationPlace(SHOW));
        addButton("Errors", new com.example.client.places.ErrorPlace(INDEX));
        addButton("Audits", new com.example.client.places.AuditPlace(INDEX));
    }
}
