package de.mkristian.ixtlan.rideboards.client.presenters;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.mkristian.gwt.rails.places.RestfulActionEnum;
import de.mkristian.ixtlan.rideboards.client.RideboardErrorHandler;
import de.mkristian.ixtlan.rideboards.client.models.Configuration;
import de.mkristian.ixtlan.rideboards.client.places.ConfigurationPlace;
import de.mkristian.ixtlan.rideboards.client.restservices.ConfigurationsRestService;
import de.mkristian.ixtlan.rideboards.client.views.ConfigurationView;

public class ConfigurationPresenter extends AbstractPresenter {

    private final ConfigurationView view;
    private final ConfigurationsRestService service;
    private final PlaceController places;

    @Inject
    public ConfigurationPresenter(RideboardErrorHandler errors, ConfigurationView view, ConfigurationsRestService service, PlaceController places){
        super(errors);
        this.view = view;
        this.view.setPresenter(this);
        this.service = service;
        this.places = places;
    }

    public void init(AcceptsOneWidget display){
        setDisplay(display);
    }

    public void save(final Configuration model){
        service.update(model, new MethodCallback<Configuration>() {
            @Override
            public void onSuccess(Method method, Configuration model) {
                places.goTo(new ConfigurationPlace(model, RestfulActionEnum.SHOW));
            }
            @Override
            public void onFailure(Method method, Throwable e) {
                onError(method, e);
            }
          });
    }

    public void show(){
        ConfigurationPlace next = new ConfigurationPlace(RestfulActionEnum.SHOW);
        if (places.getWhere().equals(next)) {
            setWidget(view);
            service.show(new MethodCallback<Configuration>() {
                @Override
                public void onSuccess(Method method, Configuration model) {
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

    public void edit(){
        ConfigurationPlace next = new ConfigurationPlace(RestfulActionEnum.EDIT);
        if (places.getWhere().equals(next)) {
            setWidget(view);
            service.show(new MethodCallback<Configuration>() {
                @Override
                public void onSuccess(Method method, Configuration model) {
                    view.edit(model);
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
