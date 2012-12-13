package de.mkristian.ixtlan.rideboards.client.activities;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import de.mkristian.gwt.rails.places.RestfulActionEnum;
import de.mkristian.ixtlan.rideboards.client.places.ConfigurationPlace;
import de.mkristian.ixtlan.rideboards.client.presenters.ConfigurationPresenter;

public class ConfigurationActivity extends AbstractActivity {

    private final ConfigurationPlace place;
    private final ConfigurationPresenter presenter;
    
    @Inject
    public ConfigurationActivity(@Assisted ConfigurationPlace place, ConfigurationPresenter presenter) {
        this.place = place;
        this.presenter = presenter;
    }

    public void start(AcceptsOneWidget display, EventBus eventBus) {
        presenter.init( display );
        switch( RestfulActionEnum.valueOf( place.action ) ){
            case EDIT:
                presenter.edit();
                break;
            case SHOW:
                if ( place.model != null ){
                    presenter.show( place.model );
                }
                else {
                    presenter.show();
                }
                break;
        }
    }

    @Override
    public String mayStop() {
        if (presenter.isDirty()){
            return "there are unsaved data.";
        }
        else {
            return null;
        }
    }
}
