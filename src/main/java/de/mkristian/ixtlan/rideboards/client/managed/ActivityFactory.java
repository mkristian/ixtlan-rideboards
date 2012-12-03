package de.mkristian.ixtlan.rideboards.client.managed;


import com.google.gwt.activity.shared.Activity;
import com.google.inject.name.Named;

import de.mkristian.ixtlan.rideboards.client.places.LoginPlace;
public interface ActivityFactory {
    @Named("errors") Activity create(de.mkristian.ixtlan.rideboards.client.places.ErrorPlace place);
    @Named("audits") Activity create(de.mkristian.ixtlan.rideboards.client.places.AuditPlace place);
    @Named("configurations") Activity create(de.mkristian.ixtlan.rideboards.client.places.ConfigurationPlace place);
    @Named("login") Activity create(LoginPlace place);
}
