package com.example.client.managed;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.example.client.models.User;

import com.google.gwt.place.shared.Place;

import de.mkristian.gwt.rails.places.RestfulPlace;
import de.mkristian.gwt.rails.places.RestfulPlaceHistoryMapper;
import de.mkristian.gwt.rails.session.SessionManager;

@Singleton
public class RideboardPlaceHistoryMapper extends RestfulPlaceHistoryMapper {

    private final SessionManager<User> manager;

    @Inject
    public RideboardPlaceHistoryMapper(SessionManager<User> manager){
        register("configurations", new com.example.client.places.ConfigurationPlaceTokenizer());
        register("errors", new com.example.client.places.ErrorPlaceTokenizer());
        register("audits", new com.example.client.places.AuditPlaceTokenizer());
        this.manager = manager;
    }

    @Override
    public Place getPlace(String token) {
        RestfulPlace<?, ?> place = (RestfulPlace<?, ?>) super.getPlace(token);
        // place needs to be different on the level of equals in order to trigger an activity
        place.hasSession = manager.hasSession(); 
        return place;
    }
}
