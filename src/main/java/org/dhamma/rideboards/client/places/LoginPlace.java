package org.dhamma.rideboards.client.places;

import org.dhamma.rideboards.client.managed.ActivityFactory;

import com.google.gwt.activity.shared.Activity;

import de.mkristian.gwt.rails.places.RestfulPlace;

public class LoginPlace extends RestfulPlace<Void, ActivityFactory> {

    public static final LoginPlace LOGIN = new LoginPlace();

    private LoginPlace() {
        super(null, null);
    }

    @Override
    public Activity create(ActivityFactory factory) {
        return factory.create(this);
    }
}
