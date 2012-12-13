package de.mkristian.ixtlan.rideboards.client;


import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import de.mkristian.gwt.rails.RemoteNotifierLabel;
import de.mkristian.gwt.rails.SessionApplication;
import de.mkristian.ixtlan.rideboards.client.managed.RideboardMenu;
import de.mkristian.ixtlan.rideboards.client.models.User;

@Singleton
public class RideboardApplication extends Composite implements SessionApplication<User> {
    
    interface Binder extends UiBinder<Widget, RideboardApplication> {}

    private static Binder BINDER = GWT.create(Binder.class);

    private final RemoteNotifierLabel notifierLabel;

    @UiField(provided=true) final SimplePanel display;
    @UiField(provided=true) final Panel header;
    @UiField(provided=true) final Panel navigation;
    @UiField(provided=true) final Panel footer;

    @Inject
    RideboardApplication( final RemoteNotifierLabel notifierLabel,
            final ActivityManager activityManager,
            final RideboardMenu menu,
            final BreadCrumbsPanel breadCrumbs,
            final ApplicationLinksPanel links ){
        this.display = new ScrollPanel();
        this.notifierLabel = notifierLabel;
        this.navigation = menu;
        this.footer = links;
        this.header = breadCrumbs;
        activityManager.setDisplay( display );
        initWidget( BINDER.createAndBindUi( this ) );
    }
    
    @Override
    public void run() {
        LayoutPanel root = RootLayoutPanel.get();
        root.add(notifierLabel);
        root.setWidgetLeftWidth(notifierLabel, 25, Unit.PCT, 50, Unit.PCT);
        root.add(this.asWidget());
    }
    
    @Override
    public void startSession(User user) {
        ((BreadCrumbsPanel) this.header).initUser(user);
        ((ApplicationLinksPanel) this.footer).initApplications(user.applications);
        this.navigation.setVisible(true);
    }

    @Override
    public void stopSession() {
        ((BreadCrumbsPanel) this.header).initUser(null);
        ((ApplicationLinksPanel) this.footer).initApplications(null);
        this.navigation.setVisible(false);
    }
}