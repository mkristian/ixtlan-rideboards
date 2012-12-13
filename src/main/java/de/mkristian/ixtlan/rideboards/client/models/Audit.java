package de.mkristian.ixtlan.rideboards.client.models;


import java.util.Date;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.fusesource.restygwt.client.Json;
import org.fusesource.restygwt.client.Json.Style;

import de.mkristian.gwt.rails.models.HasToDisplay;
import de.mkristian.gwt.rails.models.Identifyable;

@Json(style = Style.RAILS)
public class Audit implements HasToDisplay, Identifyable {

  public final int id;

  @Json(name = "created_at")
  private final Date createdAt;

  private final String login;

  private final String message;

  @JsonCreator
  public Audit(@JsonProperty("id") int id, 
          @JsonProperty("login") String login,
          @JsonProperty("message") String message, 
          @JsonProperty("createdAt") Date createdAt){
    this.id = id;
    this.login = login;
    this.message = message;
    this.createdAt = createdAt;
  }

  public int getId(){
    return id;
  }

  public Date getCreatedAt(){
    return createdAt;
  }

  public String getLogin(){
    return login;
  }

  public String getMessage(){
    return message;
  }

  public int hashCode(){
    return id;
  }

  public boolean equals(Object other){
    return (other instanceof Audit) && 
        ((Audit)other).id == id;
  }

  public String toDisplay() {
    return login;
  }
}
