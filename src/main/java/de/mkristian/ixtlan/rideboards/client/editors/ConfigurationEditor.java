package de.mkristian.ixtlan.rideboards.client.editors;


import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import de.mkristian.gwt.rails.editors.UserLabel;
import de.mkristian.gwt.rails.editors.IntegerBox;
import de.mkristian.ixtlan.rideboards.client.models.Configuration;
import de.mkristian.ixtlan.rideboards.client.models.User;

public class ConfigurationEditor extends Composite implements Editor<Configuration>{
    
    interface Binder extends UiBinder<Widget, ConfigurationEditor> {}

    private static final Binder BINDER = GWT.create(Binder.class);
    
    @Ignore @UiField FlowPanel signature;

    @UiField DateLabel createdAt;
    @UiField DateLabel updatedAt;
    @UiField UserLabel<User> modifiedBy;

    @UiField IntegerBox errorsKeepDumps;

    @UiField TextBox errorsBaseUrl;

    @UiField TextBox errorsFromEmail;

    @UiField TextBox errorsToEmails;

    @UiField IntegerBox idleSessionTimeout;

    @UiField IntegerBox auditsKeepLogs;

    public ConfigurationEditor() {
        initWidget(BINDER.createAndBindUi(this));
    }

    public void resetSignature() {
        this.signature.setVisible(createdAt.getValue() != null);
    }

    public void setEnabled(boolean enabled) {
        resetSignature();
        this.errorsKeepDumps.setEnabled(enabled);
        this.errorsBaseUrl.setEnabled(enabled);
        this.errorsFromEmail.setEnabled(enabled);
        this.errorsToEmails.setEnabled(enabled);
        this.idleSessionTimeout.setEnabled(enabled);
        this.auditsKeepLogs.setEnabled(enabled);
    }
}
