package de.mkristian.ixtlan.rideboards.client.views;

import java.util.List;


import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.rideboards.client.models.Error;
import de.mkristian.ixtlan.rideboards.client.presenters.ErrorPresenter;

@ImplementedBy(ErrorListViewImpl.class)
public interface ErrorListView extends IsWidget {

    void setPresenter(ErrorPresenter presenter);

    void reset(List<Error> models);
}