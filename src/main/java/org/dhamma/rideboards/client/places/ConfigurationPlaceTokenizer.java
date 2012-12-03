package org.dhamma.rideboards.client.places;

import de.mkristian.gwt.rails.places.RestfulAction;
import de.mkristian.gwt.rails.places.RestfulPlaceTokenizer;

public class ConfigurationPlaceTokenizer extends RestfulPlaceTokenizer<ConfigurationPlace> {
    
    public ConfigurationPlace getPlace(String token) {
        return new ConfigurationPlace(toSingletonToken(token).action);
    }

    @Override
    protected ConfigurationPlace newRestfulPlace(RestfulAction action) {
        throw new RuntimeException("not used");
    }

    @Override
    protected ConfigurationPlace newRestfulPlace(int id, RestfulAction action) {
        throw new RuntimeException("not used");
    }
}
