package de.mkristian.ixtlan.rideboards.client.managed;

import static de.mkristian.gwt.rails.places.RestfulActionEnum.INDEX;
import static de.mkristian.gwt.rails.places.RestfulActionEnum.SHOW;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.place.shared.PlaceController;

import de.mkristian.gwt.rails.session.Guard;
import de.mkristian.gwt.rails.views.SessionMenu;

@Singleton
public class RideboardMenu extends SessionMenu {

    @Inject
    RideboardMenu(final PlaceController placeController, Guard guard){
        super(placeController, guard);
        addButton("Errors", new de.mkristian.ixtlan.rideboards.client.places.ErrorPlace(INDEX));
        addButton("Audits", new de.mkristian.ixtlan.rideboards.client.places.AuditPlace(INDEX));
        addButton("Configuration", new de.mkristian.ixtlan.rideboards.client.places.ConfigurationPlace(SHOW));
    }
}
