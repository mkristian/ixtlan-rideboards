package de.mkristian.ixtlan.rideboards.client.presenters;

import java.util.List;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.mkristian.gwt.rails.places.RestfulActionEnum;
import de.mkristian.ixtlan.rideboards.client.RideboardErrorHandler;
import de.mkristian.ixtlan.rideboards.client.models.Error;
import de.mkristian.ixtlan.rideboards.client.places.ErrorPlace;
import de.mkristian.ixtlan.rideboards.client.restservices.ErrorsRestService;
import de.mkristian.ixtlan.rideboards.client.views.ErrorListView;
import de.mkristian.ixtlan.rideboards.client.views.ErrorView;

public class ErrorPresenter extends AbstractPresenter {

    private final ErrorView view;
    private final ErrorListView listView;
    private final ErrorsRestService service;
    private final PlaceController places;

    @Inject
    public ErrorPresenter(RideboardErrorHandler errors, ErrorView view, ErrorListView listView, ErrorsRestService service, PlaceController places){
        super(errors);
        this.view = view;
        this.view.setPresenter(this);
        this.listView = listView;
        this.listView.setPresenter(this);
        this.service = service;
        this.places = places;
    }

    public void init(AcceptsOneWidget display){
        setDisplay(display);
    }

    public void listAll(){
        ErrorPlace next = new ErrorPlace(RestfulActionEnum.INDEX);
        if (places.getWhere().equals(next)) {
            setWidget(listView);
            service.index(new MethodCallback<List<Error>>() {
                @Override
                public void onSuccess(Method method, List<Error> models) {
                    listView.reset(models);
                }
                @Override
                public void onFailure(Method method, Throwable e) {
                    onError(method, e);   
                }
            });
        }
        else {
            places.goTo(next);
        }
    }

    public void show(int id){
        ErrorPlace next = new ErrorPlace(id, RestfulActionEnum.SHOW);
        if (places.getWhere().equals(next)) {
            setWidget(view);
            service.show(id, new MethodCallback<Error>() {
                @Override
                public void onSuccess(Method method, Error model) {
                    view.show(model);
                }
                @Override
                public void onFailure(Method method, Throwable e) {
		        onError(method, e);   
                }
              });
        }
        else {
            places.goTo(next);
        }
    }

    private void onError(Method method, Throwable e) {
        errors.onError(method, e);
    }

    public boolean isDirty() {
        return view.isDirty();
    }
}
