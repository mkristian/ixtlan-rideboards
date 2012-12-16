package de.mkristian.ixtlan.rideboards.client;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.user.client.ui.Label;

import de.mkristian.gwt.rails.ErrorHandlerWithDisplay;
import de.mkristian.gwt.rails.Notice;

@Singleton
public class RideboardsErrorHandler extends ErrorHandlerWithDisplay {

    @Inject
    public RideboardsErrorHandler(Notice notice) {
        super(notice);
    }

    // @Override
    // protected void generalError(Method method) {
    //     show("Error");
    // }

    // @Override
    // protected void undefinedStatus(Method method) {
    //     if (method != null) {
    //         show("Error: " + method.getResponse().getStatusText());
    //     }
    //     else {
    //         show("Error");
    //     }
    // }

    // @Override
    // protected void conflict(Method method) {
    //     show("Conflict! Data was modified by someone else. Please reload the data.");
    // }

    // @Override
    // protected void unprocessableEntity(Method method) {
    //     showErrors(method);
    // }

    // @Override
    // protected void forbidden(Method method) {
    // 	display.setWidget(new Label("Forbidden."));
    // }

    @Override
    protected void notFound(Method method) {
    	getDisplay().setWidget(new Label("Resource Not Found."));
    }
}