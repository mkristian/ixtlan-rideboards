package de.mkristian.ixtlan.rideboards.client.views;


import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.mkristian.gwt.rails.places.RestfulActionEnum;
import de.mkristian.ixtlan.rideboards.client.editors.ErrorEditor;
import de.mkristian.ixtlan.rideboards.client.models.Error;
import de.mkristian.ixtlan.rideboards.client.places.ErrorPlace;

@Singleton
public class ErrorViewImpl extends Composite implements ErrorView {

  @UiTemplate("ErrorView.ui.xml")
  interface Binder extends UiBinder<Widget, ErrorViewImpl> {}

  interface EditorDriver extends SimpleBeanEditorDriver<Error, ErrorEditor> {}

  private final Binder BINDER = GWT.create(Binder.class);

  private final EditorDriver editorDriver = GWT.create(EditorDriver.class);

  private final PlaceController places;

  @UiField ErrorEditor editor;
  @UiField Button list;

  @Inject
  public ErrorViewImpl(PlaceController places) {
      this.places = places;
      initWidget(BINDER.createAndBindUi(this));
      editorDriver.initialize(editor);
  }


  @Override
  public void show(Error model){
      editorDriver.edit(model);
      editor.setEnabled(false);
  }

  @UiHandler("list")
  void onListClick(ClickEvent event) {
      places.goTo( new ErrorPlace( RestfulActionEnum.INDEX ) );
  }
}
