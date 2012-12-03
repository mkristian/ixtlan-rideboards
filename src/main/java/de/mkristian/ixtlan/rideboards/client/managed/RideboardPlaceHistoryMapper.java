package de.mkristian.ixtlan.rideboards.client.managed;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.mkristian.gwt.rails.places.SessionRestfulPlaceHistoryMapper;
import de.mkristian.gwt.rails.session.HasSession;

@Singleton
public class RideboardPlaceHistoryMapper extends SessionRestfulPlaceHistoryMapper {

    @Inject
    public RideboardPlaceHistoryMapper(HasSession session){
        super(session);
        register("errors", new de.mkristian.ixtlan.rideboards.client.places.ErrorPlaceTokenizer());
        register("audits", new de.mkristian.ixtlan.rideboards.client.places.AuditPlaceTokenizer());
        register("configurations", new de.mkristian.ixtlan.rideboards.client.places.ConfigurationPlaceTokenizer());
    }  
}
