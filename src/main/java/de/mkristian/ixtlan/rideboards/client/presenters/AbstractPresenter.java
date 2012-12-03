package de.mkristian.ixtlan.rideboards.client.presenters;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

import de.mkristian.ixtlan.rideboards.client.RideboardErrorHandler;


public class AbstractPresenter {

    protected final RideboardErrorHandler errors;
    private AcceptsOneWidget display;

    public AbstractPresenter(RideboardErrorHandler errors){
        this.errors = errors;
    }

    public void setDisplay(AcceptsOneWidget display){
        this.display = display;
        this.errors.setDisplay(display);
    }

    protected void setWidget(IsWidget view) {
        display.setWidget(view.asWidget());
    }
}
