package de.mkristian.ixtlan.rideboards.client.restservices;

import de.mkristian.gwt.rails.dispatchers.RestfulDispatcherSingleton;
import de.mkristian.ixtlan.rideboards.client.models.Audit;

import java.util.List;

import javax.ws.rs.*;

import org.fusesource.restygwt.client.Attribute;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;


@Options(dispatcher = RestfulDispatcherSingleton.class)
public interface AuditsRestService extends RestService {

  @GET @Path("/audits")
  void index(MethodCallback<List<Audit>> callback);

  @GET @Path("/audits/{id}")
  void show(@PathParam("id") int id, MethodCallback<Audit> callback);

}
