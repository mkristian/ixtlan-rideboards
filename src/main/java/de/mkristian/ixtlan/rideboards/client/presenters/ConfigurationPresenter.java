package de.mkristian.ixtlan.rideboards.client.presenters;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.mkristian.gwt.rails.RemoteNotifier;
import de.mkristian.gwt.rails.places.RestfulActionEnum;
import de.mkristian.gwt.rails.presenters.AbstractPresenter;
import de.mkristian.ixtlan.rideboards.client.RideboardErrorHandler;
import de.mkristian.ixtlan.rideboards.client.models.Configuration;
import de.mkristian.ixtlan.rideboards.client.places.ConfigurationPlace;
import de.mkristian.ixtlan.rideboards.client.restservices.ConfigurationsRestService;
import de.mkristian.ixtlan.rideboards.client.views.ConfigurationView;

public class ConfigurationPresenter extends AbstractPresenter {

    private final ConfigurationView view;
    private final ConfigurationsRestService service;
    private final PlaceController places;
    private final RemoteNotifier notifier;
    
    private boolean isEditing = false;

    @Inject
    public ConfigurationPresenter(RemoteNotifier notifier,
                RideboardErrorHandler errors,
                ConfigurationView view,
                ConfigurationsRestService service,
                PlaceController places){
        super(errors);
        this.notifier = notifier;
        this.view = view;
        this.view.setPresenter(this);
        this.service = service;
        this.places = places;
    }

    public void init(AcceptsOneWidget display){
        setDisplay(display);
    }

    public void save(final Configuration model){
        notifier.saving();
        service.update(model, new MethodCallback<Configuration>() {
            @Override
            public void onSuccess(Method method, Configuration model) {
                notifier.finish();
                isEditing = false;
                places.goTo(new ConfigurationPlace(model, RestfulActionEnum.SHOW));
            }
            @Override
            public void onFailure(Method method, Throwable e) {
                onError(method, e);
            }
          });
    }

    public void reload() {
        if( isEditing ) {
            edit();
        }
        else {
            show();
        }
    }

    
    public void show( Configuration model ){
        isEditing = false;
        setWidget( view );
        view.show( model );
    }
    
    public void show(){
        notifier.loading();
        isEditing = false;
        setWidget(view);
        service.show(new MethodCallback<Configuration>() {
            @Override
            public void onSuccess(Method method, Configuration model) {
                notifier.finish();
                view.show(model);
            }
            @Override
            public void onFailure(Method method, Throwable e) {
                onError(method, e);   
            }
        });
    }

    public void edit(){
        notifier.loading();
        isEditing = true;
        setWidget(view);
        service.show(new MethodCallback<Configuration>() {
            @Override
            public void onSuccess(Method method, Configuration model) {
                notifier.finish();
                view.edit(model);
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

    public boolean isDirty() {
        return isEditing && view.isDirty();
    }
}
