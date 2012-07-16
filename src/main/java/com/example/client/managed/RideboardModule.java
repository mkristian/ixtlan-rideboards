package com.example.client.managed;

import com.example.client.RideboardApplication;
import com.example.client.SessionActivityPlaceActivityMapper;
import com.example.client.activities.LoginActivity;
import de.mkristian.gwt.rails.Application;
import de.mkristian.gwt.rails.BaseModule;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import com.example.client.views.LoginViewImpl;

import de.mkristian.gwt.rails.session.LoginView;
public class RideboardModule extends BaseModule {

    @Override
    protected void configure() {
        super.configure();
        bind(com.example.client.restservices.ConfigurationsRestService.class).toProvider(ConfigurationsRestServiceProvider.class);
        bind(com.example.client.restservices.ErrorsRestService.class).toProvider(ErrorsRestServiceProvider.class);
        bind(com.example.client.restservices.AuditsRestService.class).toProvider(AuditsRestServiceProvider.class);
        bind(Application.class).to(RideboardApplication.class);
        bind(PlaceHistoryMapper.class).to(RideboardPlaceHistoryMapper.class).in(Singleton.class);
        bind(ActivityMapper.class).to(SessionActivityPlaceActivityMapper.class).in(Singleton.class);
        bind(LoginView.class).to(LoginViewImpl.class);
        install(new GinFactoryModuleBuilder()
            .implement(Activity.class, Names.named("configurations"), com.example.client.activities.ConfigurationActivity.class)
            .implement(Activity.class, Names.named("errors"), com.example.client.activities.ErrorActivity.class)
            .implement(Activity.class, Names.named("audits"), com.example.client.activities.AuditActivity.class)
		.implement(Activity.class, Names.named("login"), LoginActivity.class)
            .build(ActivityFactory.class));
    }

    @Singleton
    public static class AuditsRestServiceProvider implements Provider<com.example.client.restservices.AuditsRestService> {
        private final com.example.client.restservices.AuditsRestService service = GWT.create(com.example.client.restservices.AuditsRestService.class);
        public com.example.client.restservices.AuditsRestService get() {
            return service;
        }
    }

    @Singleton
    public static class ErrorsRestServiceProvider implements Provider<com.example.client.restservices.ErrorsRestService> {
        private final com.example.client.restservices.ErrorsRestService service = GWT.create(com.example.client.restservices.ErrorsRestService.class);
        public com.example.client.restservices.ErrorsRestService get() {
            return service;
        }
    }

    @Singleton
    public static class ConfigurationsRestServiceProvider implements Provider<com.example.client.restservices.ConfigurationsRestService> {
        private final com.example.client.restservices.ConfigurationsRestService service = GWT.create(com.example.client.restservices.ConfigurationsRestService.class);
        public com.example.client.restservices.ConfigurationsRestService get() {
            return service;
        }
    }
}


