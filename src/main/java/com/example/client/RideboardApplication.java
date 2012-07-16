package com.example.client;

import com.example.client.managed.RideboardMenuPanel;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;

import de.mkristian.gwt.rails.Application;
import de.mkristian.gwt.rails.Notice;

public class RideboardApplication implements Application {
    private final SimplePanel panel = new SimplePanel();
    private final Notice notice;
    private final BreadCrumbsPanel breadCrumbs;
    private final RideboardMenuPanel menu;
    private final ApplicationLinksPanel links;

    @Inject
    RideboardApplication(final Notice notice,
            final BreadCrumbsPanel breadCrumbs,
            final RideboardMenuPanel menu,
            final ActivityManager activityManager,
            final ApplicationLinksPanel links){
        activityManager.setDisplay(panel);
        this.notice = notice;
        this.breadCrumbs = breadCrumbs;
        this.menu = menu;
        this.links = links;
    }
    
    @Override
    public void run() {
        Panel root = RootPanel.get();
        root.add(notice);
        root.add(breadCrumbs);
        root.add(menu);
        root.add(panel);
        root.add(links);
    }
}