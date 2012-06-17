package com.example.client.activities;

import java.util.List;

import com.example.client.events.ErrorEvent;

import com.example.client.models.Error;
import com.example.client.places.ErrorPlace;
import com.example.client.restservices.ErrorsRestService;
import com.example.client.views.ErrorView;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import de.mkristian.gwt.rails.DisplayErrors;
import de.mkristian.gwt.rails.Notice;
import de.mkristian.gwt.rails.events.ModelEvent.Action;
import de.mkristian.gwt.rails.places.RestfulActionEnum;

public class ErrorActivity extends AbstractActivity implements ErrorView.Presenter{

    private final ErrorPlace place;
    private final ErrorsRestService service;
    private final Notice notice;
    private final DisplayErrors errors;
    private final PlaceController placeController;
    private final ErrorView view;
    private EventBus eventBus;
    
    @Inject
    public ErrorActivity(@Assisted ErrorPlace place, final Notice notice, DisplayErrors errors,
            final ErrorView view, ErrorsRestService service, PlaceController placeController) {
        this.place = place;
        this.notice = notice;
        this.errors = errors;
        this.view = view;
        this.service = service;
        this.placeController = placeController;

        notice.hide();
        view.setup(this, place.action);
    }

    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.eventBus = eventBus;

        display.setWidget(view.asWidget());

        switch(RestfulActionEnum.valueOf(place.action)){
            case SHOW:
                load(place.id);
                break;
            case INDEX:
            default:
                load();
                break;
        }
    }

    public void goTo(Place place) {
        placeController.goTo(place);
    }

    public void load(){
        service.index(new MethodCallback<List<Error>>() {

            public void onFailure(Method method, Throwable exception) {
                notice.finishLoading();
                notice.error("error loading list of Error", exception);
            }

            public void onSuccess(Method method, List<Error> response) {
                notice.finishLoading();
                eventBus.fireEvent(new ErrorEvent(response, Action.LOAD));
                view.reset(response);
            }
        });
        notice.loading();
    }

    public void load(int id) {
        view.edit(new Error()); // clear the form
        service.show(id, new MethodCallback<Error>() {

            public void onFailure(Method method, Throwable exception) {
                notice.finishLoading();
                notice.error("error loading Error", exception);
            }

            public void onSuccess(Method method, Error response) {
                notice.finishLoading();
                eventBus.fireEvent(new ErrorEvent(response, Action.LOAD));
                view.edit(response);
            }
        });
        notice.loading();
    }
}