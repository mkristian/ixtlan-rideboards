package de.mkristian.ixtlan.rideboards.client.activities;

import javax.inject.Inject;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.assistedinject.Assisted;

import de.mkristian.gwt.rails.session.LoginView;
import de.mkristian.ixtlan.rideboards.client.places.LoginPlace;

public class LoginActivity extends AbstractActivity {//implements LoginView.Presenter{

    private final LoginView view;

    @Inject
    public LoginActivity( @Assisted LoginPlace place,
            LoginView view,
            LoginView.Presenter presenter ) {
        view.setPresenter( presenter );
        this.view = view;
    }

    public void start( AcceptsOneWidget display, EventBus eventBus ) {
        GWT.log("============="+view.asWidget() );
        display.setWidget( view.asWidget() );
    }
}