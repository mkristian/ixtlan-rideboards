package de.mkristian.ixtlan.rideboards.client;


import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.PlaceController.Delegate;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

import de.mkristian.gwt.rails.Application;
import de.mkristian.gwt.rails.session.Guard;
import de.mkristian.gwt.rails.session.HasSession;
import de.mkristian.gwt.rails.session.LoginView;
import de.mkristian.gwt.rails.session.SessionManager;
import de.mkristian.ixtlan.rideboards.client.managed.ManagedGinModule;
import de.mkristian.ixtlan.rideboards.client.managed.RideboardPlaceHistoryMapper;
import de.mkristian.ixtlan.rideboards.client.models.User;
import de.mkristian.ixtlan.rideboards.client.presenters.LoginPresenter;
import de.mkristian.ixtlan.rideboards.client.views.LoginViewImpl;

public class RideboardGinModule extends ManagedGinModule {

    @Override
    protected void configure() {
        super.configure();
        bind(Application.class).to(RideboardApplication.class);
        bind(LoginView.Presenter.class).to(LoginPresenter.class);
        bind(Guard.class).to(Key.get(new TypeLiteral<SessionManager<User>>() {})).in(Singleton.class);
        bind(HasSession.class).to(Key.get(new TypeLiteral<SessionManager<User>>() {})).in(Singleton.class);
        bind(PlaceHistoryMapper.class).to(RideboardPlaceHistoryMapper.class).in(Singleton.class);
        bind(ActivityMapper.class).to(SessionActivityPlaceActivityMapper.class).in(Singleton.class);
        bind(Delegate.class).to(RideboardConfirmation.class);
        bind(LoginView.class).to(LoginViewImpl.class);
        bind(RideboardConfirmation.class);
    }

}




