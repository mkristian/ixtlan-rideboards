package org.dhamma.rideboards.client.managed;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.mkristian.gwt.rails.places.SessionRestfulPlaceHistoryMapper;
import de.mkristian.gwt.rails.session.HasSession;

@Singleton
public class RideboardPlaceHistoryMapper extends SessionRestfulPlaceHistoryMapper {

    @Inject
    public RideboardPlaceHistoryMapper(HasSession session){
        super(session);
        register("errors", new org.dhamma.rideboards.client.places.ErrorPlaceTokenizer());
        register("audits", new org.dhamma.rideboards.client.places.AuditPlaceTokenizer());
        register("configurations", new org.dhamma.rideboards.client.places.ConfigurationPlaceTokenizer());
    }  
}
