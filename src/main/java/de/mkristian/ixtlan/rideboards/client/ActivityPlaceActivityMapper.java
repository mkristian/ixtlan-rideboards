package de.mkristian.ixtlan.rideboards.client;


import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import de.mkristian.gwt.rails.Notice;
import de.mkristian.gwt.rails.places.RestfulPlace;
import de.mkristian.ixtlan.rideboards.client.managed.ActivityFactory;

public class ActivityPlaceActivityMapper implements ActivityMapper {
    protected final ActivityFactory factory;
    protected final Notice notice;

    @Inject
    public ActivityPlaceActivityMapper(ActivityFactory factory, Notice notice) {
        this.notice = notice;
        this.factory = factory;
    }

    @SuppressWarnings("unchecked")
    public Activity getActivity(Place place) {
        if (place instanceof RestfulPlace<?, ?>) {
            GWT.log(place.toString());
            return ((RestfulPlace<?, ActivityFactory>) place).create(factory);
        }
        notice.warn("nothing to see");
        return null;
    }
}
