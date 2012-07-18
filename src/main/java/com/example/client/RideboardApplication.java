package com.example.client;

import com.example.client.managed.RideboardMenuPanel;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import de.mkristian.gwt.rails.Application;
import de.mkristian.gwt.rails.Notice;

public class RideboardApplication extends Composite implements Application {
    
    interface Binder extends UiBinder<Widget, RideboardApplication> {}

    private static Binder BINDER = GWT.create(Binder.class);

    private final Notice notice;
    @UiField(provided=true) final SimplePanel display = new ScrollPanel();
    @UiField(provided=true) Panel header;
    @UiField(provided=true) Panel navigation;
    @UiField(provided=true) Panel footer;

    @Inject
    RideboardApplication(final Notice notice,
            final BreadCrumbsPanel breadCrumbs,
            final RideboardMenuPanel menu,
            final ActivityManager activityManager,
            final ApplicationLinksPanel links){
        activityManager.setDisplay(display);
        this.notice = notice;
        this.header = breadCrumbs;
        this.navigation = menu;
        this.footer = links;
        initWidget(BINDER.createAndBindUi(this));
    }
    
    @Override
    public void run() {
        LayoutPanel root = RootLayoutPanel.get();
        root.add(notice);
        root.setWidgetLeftWidth(notice, 25, Unit.PCT, 50, Unit.PCT);
        root.add(this.asWidget());
    }
}