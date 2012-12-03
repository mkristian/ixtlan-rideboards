package de.mkristian.ixtlan.rideboards.client.managed;


import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceController.Delegate;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import de.mkristian.gwt.rails.Application;
import de.mkristian.gwt.rails.BaseModule;
import de.mkristian.gwt.rails.session.LoginView;
import de.mkristian.ixtlan.rideboards.client.RideboardApplication;
import de.mkristian.ixtlan.rideboards.client.RideboardConfirmation;
import de.mkristian.ixtlan.rideboards.client.SessionActivityPlaceActivityMapper;
import de.mkristian.ixtlan.rideboards.client.activities.LoginActivity;
import de.mkristian.ixtlan.rideboards.client.views.LoginViewImpl;

public class RideboardModule extends BaseModule {

    @Override
    protected void configure() {
        super.configure();
        bind(de.mkristian.ixtlan.rideboards.client.restservices.ErrorsRestService.class).toProvider(ErrorsRestServiceProvider.class);
        bind(de.mkristian.ixtlan.rideboards.client.restservices.AuditsRestService.class).toProvider(AuditsRestServiceProvider.class);
        bind(de.mkristian.ixtlan.rideboards.client.restservices.ConfigurationsRestService.class).toProvider(ConfigurationsRestServiceProvider.class);
        bind(Application.class).to(RideboardApplication.class);
        bind(PlaceHistoryMapper.class).to(RideboardPlaceHistoryMapper.class).in(Singleton.class);
        bind(ActivityMapper.class).to(SessionActivityPlaceActivityMapper.class).in(Singleton.class);
        bind(Delegate.class).to(RideboardConfirmation.class);
        bind(RideboardConfirmation.class);
        bind(LoginView.class).to(LoginViewImpl.class);
        install(new GinFactoryModuleBuilder()
            .implement(Activity.class, Names.named("errors"), de.mkristian.ixtlan.rideboards.client.activities.ErrorActivity.class)
            .implement(Activity.class, Names.named("audits"), de.mkristian.ixtlan.rideboards.client.activities.AuditActivity.class)
            .implement(Activity.class, Names.named("configurations"), de.mkristian.ixtlan.rideboards.client.activities.ConfigurationActivity.class)
            .implement(Activity.class, Names.named("login"), LoginActivity.class)
            .build(ActivityFactory.class));
    }

    @Singleton
    public static class ConfigurationsRestServiceProvider implements Provider<de.mkristian.ixtlan.rideboards.client.restservices.ConfigurationsRestService> {
        private final de.mkristian.ixtlan.rideboards.client.restservices.ConfigurationsRestService service = GWT.create(de.mkristian.ixtlan.rideboards.client.restservices.ConfigurationsRestService.class);
        public de.mkristian.ixtlan.rideboards.client.restservices.ConfigurationsRestService get() {
            return service;
        }
    }

    @Singleton
    public static class AuditsRestServiceProvider implements Provider<de.mkristian.ixtlan.rideboards.client.restservices.AuditsRestService> {
        private final de.mkristian.ixtlan.rideboards.client.restservices.AuditsRestService service = GWT.create(de.mkristian.ixtlan.rideboards.client.restservices.AuditsRestService.class);
        public de.mkristian.ixtlan.rideboards.client.restservices.AuditsRestService get() {
            return service;
        }
    }

    @Singleton
    public static class ErrorsRestServiceProvider implements Provider<de.mkristian.ixtlan.rideboards.client.restservices.ErrorsRestService> {
        private final de.mkristian.ixtlan.rideboards.client.restservices.ErrorsRestService service = GWT.create(de.mkristian.ixtlan.rideboards.client.restservices.ErrorsRestService.class);
        public de.mkristian.ixtlan.rideboards.client.restservices.ErrorsRestService get() {
            return service;
        }
    }
}




