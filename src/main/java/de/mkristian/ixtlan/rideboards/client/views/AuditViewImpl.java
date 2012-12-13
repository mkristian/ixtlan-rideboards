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
import de.mkristian.ixtlan.rideboards.client.editors.AuditEditor;
import de.mkristian.ixtlan.rideboards.client.models.Audit;
import de.mkristian.ixtlan.rideboards.client.places.AuditPlace;

@Singleton
public class AuditViewImpl extends Composite implements AuditView {

  @UiTemplate("AuditView.ui.xml")
  interface Binder extends UiBinder<Widget, AuditViewImpl> {}

  interface EditorDriver extends SimpleBeanEditorDriver<Audit, AuditEditor> {}

  private final Binder BINDER = GWT.create(Binder.class);

  private final EditorDriver editorDriver = GWT.create(EditorDriver.class);

  private final PlaceController places;
  
  @UiField AuditEditor editor;
  @UiField Button list;

  @Inject
  public AuditViewImpl(PlaceController places) {
      this.places = places;
      initWidget(BINDER.createAndBindUi(this));
      editorDriver.initialize(editor);
  }

  @Override
  public void show(Audit model){
      editorDriver.edit(model);
      editor.setEnabled(false);
  }

  @UiHandler("list")
  void onListClick( ClickEvent event ) {
      places.goTo( new AuditPlace( RestfulActionEnum.INDEX ) );
  }
}
