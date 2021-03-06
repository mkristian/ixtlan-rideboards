package de.mkristian.ixtlan.rideboards.client.restservices;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import de.mkristian.gwt.rails.session.Authentication;
import de.mkristian.gwt.rails.session.Session;
import de.mkristian.ixtlan.rideboards.client.models.User;

@Path("/session")
public interface SessionRestService extends RestService {

    @POST
    void create(Authentication authentication, MethodCallback<Session<User>> callback);

    @DELETE
    void destroy(MethodCallback<Void> callback);

    @POST @Path("/reset_password")
    void resetPassword(Authentication authentication, MethodCallback<Void> methodCallback);
}
