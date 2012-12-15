package de.mkristian.ixtlan.rideboards.client.presenters;

import java.util.List;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.mkristian.gwt.rails.RemoteNotifier;
import de.mkristian.gwt.rails.presenters.AbstractPresenter;
import de.mkristian.ixtlan.rideboards.client.RideboardErrorHandler;
import de.mkristian.ixtlan.rideboards.client.models.Error;
import de.mkristian.ixtlan.rideboards.client.restservices.ErrorsRestService;
import de.mkristian.ixtlan.rideboards.client.views.ErrorListView;
import de.mkristian.ixtlan.rideboards.client.views.ErrorView;

public class ErrorPresenter extends AbstractPresenter {

    private final ErrorView view;
    private final ErrorListView listView;
    private final ErrorsRestService service;
    private final RemoteNotifier notifier;

    @Inject
    public ErrorPresenter( RemoteNotifier notifier,
            RideboardErrorHandler errors,
            ErrorView view,
            ErrorListView listView,
            ErrorsRestService service ){
        super(errors);
        this.notifier = notifier;
        this.view = view;
        this.listView = listView;
        this.service = service;
    }

    public void init(AcceptsOneWidget display){
        setDisplay(display);
    }

    public void listAll(){
        notifier.loading();
        setWidget(listView);
        service.index(new MethodCallback<List<Error>>() {
            @Override
            public void onSuccess(Method method, List<Error> models) {
                notifier.finish();
                listView.reset(models);
            }
            @Override
            public void onFailure(Method method, Throwable e) {
                onError(method, e);   
            }
        });
    }

    public void show(int id){
        notifier.loading();
        setWidget(view);
        service.show(id, new MethodCallback<Error>() {
            @Override
            public void onSuccess(Method method, Error model) {
                notifier.finish();
                view.show(model);
            }
            @Override
            public void onFailure(Method method, Throwable e) {
                onError(method, e);   
            }
        });
    }

    private void onError(Method method, Throwable e) {
        notifier.finish();
        errors.onError(method, e);
    }
}
