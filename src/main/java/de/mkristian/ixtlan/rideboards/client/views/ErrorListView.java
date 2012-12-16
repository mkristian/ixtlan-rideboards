package de.mkristian.ixtlan.rideboards.client.views;

import de.mkristian.ixtlan.rideboards.client.models.Error;

import com.google.inject.ImplementedBy;

import de.mkristian.gwt.rails.views.ReadOnlyListView;

@ImplementedBy(ErrorListViewImpl.class)
public interface ErrorListView extends ReadOnlyListView<Error> {
}