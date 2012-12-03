package org.dhamma.rideboards.client.presenters;

import org.dhamma.rideboards.client.RideboardErrorHandler;
import org.dhamma.rideboards.client.models.Audit;
import org.dhamma.rideboards.client.places.AuditPlace;
import org.dhamma.rideboards.client.views.AuditListView;
import org.dhamma.rideboards.client.views.AuditView;
import org.dhamma.rideboards.client.restservices.AuditsRestService;

import java.util.List;

import javax.inject.Inject;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import de.mkristian.gwt.rails.ErrorHandler.Type;
import de.mkristian.gwt.rails.places.RestfulActionEnum;

public class AuditPresenter extends AbstractPresenter {

    private final AuditView view;
    private final AuditListView listView;
    private final AuditsRestService service;
    private final PlaceController places;

    @Inject
    public AuditPresenter(RideboardErrorHandler errors, AuditView view, AuditListView listView, AuditsRestService service, PlaceController places){
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
        AuditPlace next = new AuditPlace(RestfulActionEnum.INDEX);
        if (places.getWhere().equals(next)) {
            setWidget(listView);
            service.index(new MethodCallback<List<Audit>>() {
                @Override
                public void onSuccess(Method method, List<Audit> models) {
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
        AuditPlace next = new AuditPlace(id, RestfulActionEnum.SHOW);
        if (places.getWhere().equals(next)) {
            setWidget(view);
            service.show(id, new MethodCallback<Audit>() {
                @Override
                public void onSuccess(Method method, Audit model) {
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
        errors.onError(method, errors.getType(e));
    }

    private void onError(final Audit model, Method method, Throwable e) {
        Type type = errors.getType(e);
	        errors.onError(method, type);
    }

    public boolean isDirty() {
        return view.isDirty();
    }
}
