package de.mkristian.ixtlan.rideboards.client.restservices;

import de.mkristian.gwt.rails.dispatchers.RestfulDispatcherSingleton;
import de.mkristian.ixtlan.rideboards.client.models.Error;

import java.util.List;

import javax.ws.rs.*;

import org.fusesource.restygwt.client.Attribute;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;


@Options(dispatcher = RestfulDispatcherSingleton.class)
public interface ErrorsRestService extends RestService {

  @GET @Path("/errors")
  void index(MethodCallback<List<Error>> callback);

  @GET @Path("/errors/{id}")
  void show(@PathParam("id") int id, MethodCallback<Error> callback);

}
