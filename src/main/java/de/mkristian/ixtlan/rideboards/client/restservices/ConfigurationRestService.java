package de.mkristian.ixtlan.rideboards.client.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import de.mkristian.gwt.rails.dispatchers.RestfulDispatcherSingleton;
import de.mkristian.ixtlan.rideboards.client.models.Configuration;


@Path("/configuration")
@Options(dispatcher = RestfulDispatcherSingleton.class)
public interface ConfigurationRestService extends RestService {

  @GET
  void show(MethodCallback<Configuration> callback);

  @PUT
  void update(Configuration value, MethodCallback<Configuration> callback);

}
