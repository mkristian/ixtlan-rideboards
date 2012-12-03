package org.dhamma.rideboards.client.managed;

import org.dhamma.rideboards.client.places.LoginPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.inject.name.Named;
public interface ActivityFactory {
    @Named("errors") Activity create(org.dhamma.rideboards.client.places.ErrorPlace place);
    @Named("audits") Activity create(org.dhamma.rideboards.client.places.AuditPlace place);
    @Named("configurations") Activity create(org.dhamma.rideboards.client.places.ConfigurationPlace place);
    @Named("login") Activity create(LoginPlace place);
}
