package de.mkristian.ixtlan.rideboards.client.editors;


import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import de.mkristian.ixtlan.rideboards.client.models.Audit;


public class AuditEditor extends Composite implements Editor<Audit>{
    
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

    public void resetSignature() {
        this.signature.setVisible(id.getValue() != null && id.getValue() > 0);
    }

    public void setEnabled(boolean enabled) {
        resetSignature();
        this.login.setEnabled(enabled);
        this.message.setEnabled(enabled);
    }
}
