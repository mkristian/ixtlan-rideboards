package com.example.client;

import com.example.client.managed.RideboardMenuPanel;
import com.example.client.managed.RideboardModule;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

import de.mkristian.gwt.rails.Application;
import de.mkristian.gwt.rails.Notice;
import de.mkristian.gwt.rails.dispatchers.DefaultDispatcherSingleton;

import org.fusesource.restygwt.client.Defaults;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RideboardEntryPoint implements EntryPoint {

    @GinModules(RideboardModule.class)
    static public interface RideboardGinjector extends Ginjector {
        PlaceHistoryHandler getPlaceHistoryHandler();
        Application getApplication();
    }

    static public class RideboardApplication extends Application {
        private final Notice notice;
        private final BreadCrumbsPanel breadCrumbs;
        private final RideboardMenuPanel menu;
        private final ApplicationLinksPanel links;
        private RootPanel root;

        @Inject
        RideboardApplication(final Notice notice,
                                           final BreadCrumbsPanel breadCrumbs,
                                           final RideboardMenuPanel menu,
                                           final ActivityManager activityManager,
                                           final ApplicationLinksPanel links){
            super(activityManager);
            this.notice = notice;
            this.breadCrumbs = breadCrumbs;
            this.menu = menu;
            this.links = links;
        }

        protected void initApplicationPanel(Panel panel) {
            if (this.root == null) {
                this.root = RootPanel.get();
                this.root.add(notice);
                this.root.add(breadCrumbs);
                this.root.add(menu);
                this.root.add(panel);
                this.root.add(links);
            }
        }
    }

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Defaults.setServiceRoot(GWT.getModuleBaseURL().replaceFirst("[a-zA-Z0-9_]+/$", ""));
        Defaults.setDispatcher(DefaultDispatcherSingleton.INSTANCE);
        GWT.log("base url for restservices: " + Defaults.getServiceRoot());

        final RideboardGinjector injector = GWT.create(RideboardGinjector.class);

        // setup display
        injector.getApplication().run();
     
        // Goes to the place represented on URL else default place
        injector.getPlaceHistoryHandler().handleCurrentHistory();
    }
}
