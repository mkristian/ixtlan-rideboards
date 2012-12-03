package de.mkristian.ixtlan.rideboards.client.editors;


import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import de.mkristian.ixtlan.rideboards.client.models.Error;


public class ErrorEditor extends Composite implements Editor<Error>{
    
    interface Binder extends UiBinder<Widget, ErrorEditor> {}

    private static final Binder BINDER = GWT.create(Binder.class);
    
    @Ignore @UiField FlowPanel signature;

    @UiField public NumberLabel<Integer> id;
    @UiField DateLabel createdAt;
    @UiField DateLabel updatedAt;

    @UiField TextBox message;

    @UiField TextBox request;

    @UiField TextBox response;

    @UiField TextBox session;

    @UiField TextBox parameters;

    @UiField TextBox clazz;

    @UiField TextBox backtrace;

    public ErrorEditor() {
        initWidget(BINDER.createAndBindUi(this));
    }

    public void resetSignature() {
        this.signature.setVisible(id.getValue() != null && id.getValue() > 0);
    }

    public void setEnabled(boolean enabled) {
        resetSignature();
        this.message.setEnabled(enabled);
        this.request.setEnabled(enabled);
        this.response.setEnabled(enabled);
        this.session.setEnabled(enabled);
        this.parameters.setEnabled(enabled);
        this.clazz.setEnabled(enabled);
        this.backtrace.setEnabled(enabled);
    }
}
