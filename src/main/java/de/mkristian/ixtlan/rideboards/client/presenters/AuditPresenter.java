package de.mkristian.ixtlan.rideboards.client.presenters;

import java.util.List;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.mkristian.gwt.rails.RemoteNotifier;
import de.mkristian.gwt.rails.presenters.AbstractPresenter;
import de.mkristian.ixtlan.rideboards.client.RideboardErrorHandler;
import de.mkristian.ixtlan.rideboards.client.models.Audit;
import de.mkristian.ixtlan.rideboards.client.restservices.AuditsRestService;
import de.mkristian.ixtlan.rideboards.client.views.AuditListView;
import de.mkristian.ixtlan.rideboards.client.views.AuditView;

public class AuditPresenter extends AbstractPresenter {

    private final AuditView view;
    private final AuditListView listView;
    private final AuditsRestService service;
    private final RemoteNotifier notifier;

    @Inject
    public AuditPresenter(RemoteNotifier notifier,
            RideboardErrorHandler errors, 
            AuditView view, 
            AuditListView listView, 
            AuditsRestService service){
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
        service.index(new MethodCallback<List<Audit>>() {
            @Override
            public void onSuccess(Method method, List<Audit> models) {
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
        service.show(id, new MethodCallback<Audit>() {
            @Override
            public void onSuccess(Method method, Audit model) {
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
