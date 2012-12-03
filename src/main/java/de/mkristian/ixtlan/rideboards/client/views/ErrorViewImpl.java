package de.mkristian.ixtlan.rideboards.client.views;


import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.mkristian.ixtlan.rideboards.client.RideboardConfirmation;
import de.mkristian.ixtlan.rideboards.client.editors.ErrorEditor;
import de.mkristian.ixtlan.rideboards.client.models.Error;
import de.mkristian.ixtlan.rideboards.client.presenters.ErrorPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ErrorViewImpl extends Composite implements ErrorView {

  @UiTemplate("ErrorView.ui.xml")
  interface Binder extends UiBinder<Widget, ErrorViewImpl> {}

  interface EditorDriver extends SimpleBeanEditorDriver<Error, ErrorEditor> {}

  private final Binder BINDER = GWT.create(Binder.class);

  private final EditorDriver editorDriver = GWT.create(EditorDriver.class);

  private final RideboardConfirmation confirmation;  

  private ErrorPresenter presenter;
  private boolean editable = false;
  private boolean dirty = false;

  @UiField ErrorEditor editor;
  @UiField Button list;

  @Inject
  public ErrorViewImpl(RideboardConfirmation confirmation) {
      this.confirmation = confirmation;
      initWidget(BINDER.createAndBindUi(this));
      editorDriver.initialize(editor);
  }

  @Override
  public void setPresenter(ErrorPresenter presenter) {
      this.presenter = presenter;
  }

  @Override
  public void show(Error model){
      editable = false;
      editorDriver.edit(model);
      editor.setEnabled(false);
  }

  @UiHandler("list")
  void onListClick(ClickEvent event) {
      initDirty();
      presenter.listAll();
  }

  private void initDirty(){
      dirty = editable && (editorDriver == null ? false : editorDriver.isDirty());
  }

  @Override
  public boolean isDirty() {
      return dirty;
  }
}
