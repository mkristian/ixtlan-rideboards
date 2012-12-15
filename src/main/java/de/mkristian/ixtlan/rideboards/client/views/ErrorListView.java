package de.mkristian.ixtlan.rideboards.client.views;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.rideboards.client.models.Error;

@ImplementedBy(ErrorListViewImpl.class)
public interface ErrorListView extends IsWidget {

    void reset(List<Error> models);
}