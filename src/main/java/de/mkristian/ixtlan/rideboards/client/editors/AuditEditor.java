package de.mkristian.ixtlan.rideboards.client.editors;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.NumberLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.mkristian.gwt.rails.editors.EnabledEditor;
import de.mkristian.ixtlan.rideboards.client.models.Audit;


public class AuditEditor extends EnabledEditor<Audit>{
    
    interface Binder extends UiBinder<Widget, AuditEditor> {}

    private static final Binder BINDER = GWT.create(Binder.class);
    
    @Ignore @UiField FlowPanel signature;

    @UiField public NumberLabel<Integer> id;
    @UiField DateLabel createdAt;

    @UiField TextBox login;

    @UiField TextBox message;

    public AuditEditor() {
        initWidget(BINDER.createAndBindUi(this));
    }

    public void setEnabled(boolean enabled) {
        this.signature.setVisible(id.getValue() != null && id.getValue() > 0);
        this.login.setEnabled(enabled);
        this.message.setEnabled(enabled);
    }
}
