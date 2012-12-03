package de.mkristian.ixtlan.rideboards.client.views;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
 
import static de.mkristian.gwt.rails.places.RestfulActionEnum.SHOW;

import de.mkristian.gwt.rails.places.RestfulActionEnum;
import de.mkristian.gwt.rails.views.ModelButton;
import de.mkristian.ixtlan.rideboards.client.models.Audit;
import de.mkristian.ixtlan.rideboards.client.presenters.AuditPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AuditListViewImpl extends Composite implements AuditListView {

    @UiTemplate("AuditListView.ui.xml")
    interface Binder extends UiBinder<Widget, AuditListViewImpl> {}

    private static Binder BINDER = GWT.create(Binder.class);

    private AuditPresenter presenter;

    @UiField FlexTable list;

    @Inject
    public AuditListViewImpl() {
        initWidget(BINDER.createAndBindUi(this));
    }

    @Override
    public void setPresenter(AuditPresenter presenter) {
        this.presenter = presenter;
    }
    
    private final ClickHandler clickHandler = new ClickHandler() {
        
        @SuppressWarnings("unchecked")
        public void onClick(ClickEvent event) {
            ModelButton<Audit> button = (ModelButton<Audit>)event.getSource();
            switch(button.action){
                case SHOW: presenter.show(button.model.id); break; 
            }
        }
    };
 
    private Button newButton(RestfulActionEnum action, Audit model){
        ModelButton<Audit> button = new ModelButton<Audit>(action, model);
        button.addClickHandler(clickHandler);
        return button;
    }

    @Override
    public void reset(List<Audit> models) {
        list.removeAllRows();
        list.setText(0, 0, "Id");
        list.setText(0, 1, "Login");
        list.setText(0, 2, "Message");
        list.getRowFormatter().addStyleName(0, "gwt-rails-model-list-header");
        if (models != null) {
            int row = 1;
            for(Audit model: models){
                setRow(row, model);
                row++;
            }
        }
    }

    private void setRow(int row, Audit model) {
        list.setText(row, 0, model.getId() + "");
        list.setText(row, 1, model.getLogin() + "");
        list.setText(row, 2, model.getMessage() + "");

        list.setWidget(row, 3, newButton(SHOW, model));
    }
}
