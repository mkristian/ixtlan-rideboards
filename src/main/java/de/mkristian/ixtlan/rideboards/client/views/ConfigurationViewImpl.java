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
import de.mkristian.ixtlan.rideboards.client.editors.ConfigurationEditor;
import de.mkristian.ixtlan.rideboards.client.models.Configuration;
import de.mkristian.ixtlan.rideboards.client.presenters.ConfigurationPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConfigurationViewImpl extends Composite implements ConfigurationView {

  @UiTemplate("ConfigurationView.ui.xml")
  interface Binder extends UiBinder<Widget, ConfigurationViewImpl> {}

  interface EditorDriver extends SimpleBeanEditorDriver<Configuration, ConfigurationEditor> {}

  private final Binder BINDER = GWT.create(Binder.class);

  private final EditorDriver editorDriver = GWT.create(EditorDriver.class);

  private final RideboardConfirmation confirmation;  

  private ConfigurationPresenter presenter;
  private boolean editable = false;
  private boolean dirty = false;

  @UiField ConfigurationEditor editor;
  @UiField Button reload;
  @UiField Button edit;
  @UiField Button save;
  @UiField Button cancel;

  @Inject
  public ConfigurationViewImpl(RideboardConfirmation confirmation) {
      this.confirmation = confirmation;
      initWidget(BINDER.createAndBindUi(this));
      editorDriver.initialize(editor);
  }

  @Override
  public void setPresenter(ConfigurationPresenter presenter) {
      this.presenter = presenter;
  }

  @Override
  public void show(Configuration model){
      editable = false;
      reload.setVisible(false);
      edit.setVisible(true);
      save.setVisible(false);
      cancel.setVisible(false);
      editorDriver.edit(model);
      editor.setEnabled(false);
  }

  @Override
  public void reload(Configuration model){
      // inherit editable from screen before
      reload.setVisible(true);
      edit.setVisible(false);
      save.setVisible(false);
      cancel.setVisible(false);
      editorDriver.edit(model);
      editor.setEnabled(editable);
  }

  @Override
  public void edit(Configuration model){
      editable = true;
      reload.setVisible(false);
      edit.setVisible(false);
      save.setVisible(true);
      cancel.setVisible(true);
      editorDriver.edit(model);
      editor.setEnabled(true);
  }

  @UiHandler("reload")
  void onReloadClick(ClickEvent event) {
      dirty = false;
      if (editable) {
          presenter.edit();
      }
      else {
          presenter.show();
      }
  }

  @UiHandler("edit")
  void onEditClick(ClickEvent event) {
      initDirty();
      presenter.edit();
  }

  @UiHandler("save")
  void onSaveClick(ClickEvent event) {
      dirty = false;
      presenter.save(editorDriver.flush());
  }

  @UiHandler("cancel")
  void onCancelClick(ClickEvent event) {
      dirty = false;
      presenter.show();
  }

  private void initDirty(){
      dirty = editable && (editorDriver == null ? false : editorDriver.isDirty());
  }

  @Override
  public boolean isDirty() {
      return dirty;
  }
}
