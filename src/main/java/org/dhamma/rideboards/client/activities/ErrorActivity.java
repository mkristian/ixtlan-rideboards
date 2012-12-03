package org.dhamma.rideboards.client.activities;

import org.dhamma.rideboards.client.places.ErrorPlace;
import org.dhamma.rideboards.client.presenters.ErrorPresenter;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import de.mkristian.gwt.rails.places.RestfulActionEnum;

public class ErrorActivity extends AbstractActivity {

    private final ErrorPlace place;
    private final ErrorPresenter presenter;
    
    @Inject
    public ErrorActivity(@Assisted ErrorPlace place, ErrorPresenter presenter) {
        this.place = place;
        this.presenter = presenter;
    }

    public void start(AcceptsOneWidget display, EventBus eventBus) {
        presenter.init(display);
        switch(RestfulActionEnum.valueOf(place.action)){
            case SHOW:
                presenter.show(place.id);
                break;
            case INDEX:
            default:
                presenter.listAll();
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
