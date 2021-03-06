package de.mkristian.ixtlan.rideboards.client;


import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;

import de.mkristian.gwt.rails.Application;
import de.mkristian.gwt.rails.dispatchers.DefaultDispatcherSingleton;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RideboardEntryPoint implements EntryPoint {

    @GinModules(RideboardGinModule.class)
    static public interface RideboardGinjector extends Ginjector {
        PlaceHistoryHandler getPlaceHistoryHandler();
        Application getApplication();
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
