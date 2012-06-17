package com.example.client.managed;

import com.google.gwt.activity.shared.Activity;
import com.google.inject.name.Named;
import com.example.client.places.LoginPlace;
public interface ActivityFactory {
    @Named("configurations") Activity create(com.example.client.places.ConfigurationPlace place);
    @Named("errors") Activity create(com.example.client.places.ErrorPlace place);
    @Named("audits") Activity create(com.example.client.places.AuditPlace place);
    @Named("login") Activity create(LoginPlace place);
}