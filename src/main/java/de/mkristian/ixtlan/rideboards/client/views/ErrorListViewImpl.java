package de.mkristian.ixtlan.rideboards.client.views;


import static de.mkristian.gwt.rails.places.RestfulActionEnum.SHOW;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import de.mkristian.gwt.rails.places.RestfulActionEnum;
import de.mkristian.gwt.rails.views.ModelButton;
import de.mkristian.ixtlan.rideboards.client.models.Error;
import de.mkristian.ixtlan.rideboards.client.places.ErrorPlace;

@Singleton
public class ErrorListViewImpl extends Composite implements ErrorListView {

    @UiTemplate("ErrorListView.ui.xml")
    interface Binder extends UiBinder<Widget, ErrorListViewImpl> {}

    private static Binder BINDER = GWT.create(Binder.class);

    private final PlaceController places;

    @UiField FlexTable list;

    @Inject
    public ErrorListViewImpl(PlaceController places) {
        this.places = places;
        initWidget(BINDER.createAndBindUi(this));
    }

    private final ClickHandler clickHandler = new ClickHandler() {
        
        @SuppressWarnings("unchecked")
        public void onClick(ClickEvent event) {
            ModelButton<Error> button = (ModelButton<Error>)event.getSource();
            switch(button.action){
                case SHOW:
                    places.goTo( new ErrorPlace( button.model.id, 
                            RestfulActionEnum.SHOW ) );
                    break; 
            }
        }
    };
 
    private Button newButton(RestfulActionEnum action, Error model){
        ModelButton<Error> button = new ModelButton<Error>(action, model);
        button.addClickHandler(clickHandler);
        return button;
    }

    @Override
    public void reset(List<Error> models) {
        list.removeAllRows();
        list.setText(0, 0, "Id");
        list.setText(0, 1, "Message");
        list.setText(0, 2, "Request");
        list.setText(0, 3, "Response");
        list.setText(0, 4, "Session");
        list.setText(0, 5, "Parameters");
        list.setText(0, 6, "Clazz");
        list.setText(0, 7, "Backtrace");
        list.getRowFormatter().addStyleName(0, "gwt-rails-model-list-header");
        if (models != null) {
            int row = 1;
            for(Error model: models){
                setRow(row, model);
                row++;
            }
        }
    }

    private void setRow(int row, Error model) {
        list.setText(row, 0, model.getId() + "");
        list.setText(row, 1, model.getMessage() + "");
        list.setText(row, 2, model.getRequest() + "");
        list.setText(row, 3, model.getResponse() + "");
        list.setText(row, 4, model.getSession() + "");
        list.setText(row, 5, model.getParameters() + "");
        list.setText(row, 6, model.getClazz() + "");
        list.setText(row, 7, model.getBacktrace() + "");

        list.setWidget(row, 8, newButton(SHOW, model));
    }
}
