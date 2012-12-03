package de.mkristian.ixtlan.rideboards.client.activities;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import de.mkristian.gwt.rails.places.RestfulActionEnum;
import de.mkristian.ixtlan.rideboards.client.places.AuditPlace;
import de.mkristian.ixtlan.rideboards.client.presenters.AuditPresenter;

public class AuditActivity extends AbstractActivity {

    private final AuditPlace place;
    private final AuditPresenter presenter;
    
    @Inject
    public AuditActivity(@Assisted AuditPlace place, AuditPresenter presenter) {
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
