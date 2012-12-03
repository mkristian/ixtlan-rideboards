package org.dhamma.rideboards.client.views;

import java.util.List;

import org.dhamma.rideboards.client.models.Error;
import org.dhamma.rideboards.client.presenters.ErrorPresenter;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

@ImplementedBy(ErrorListViewImpl.class)
public interface ErrorListView extends IsWidget {

    void setPresenter(ErrorPresenter presenter);

    void reset(List<Error> models);
}