package com.example.client.restservices;

import de.mkristian.gwt.rails.dispatchers.RestfulDispatcherSingleton;

import javax.ws.rs.*;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import com.example.client.models.Configuration;

@Path("/configuration")
@Options(dispatcher = RestfulDispatcherSingleton.class)
public interface ConfigurationsRestService extends RestService {

  @GET
  void show(MethodCallback<Configuration> callback);

  @PUT
  void update(Configuration value, MethodCallback<Configuration> callback);

}
