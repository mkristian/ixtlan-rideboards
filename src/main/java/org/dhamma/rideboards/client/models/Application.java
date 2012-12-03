package org.dhamma.rideboards.client.models;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.fusesource.restygwt.client.Json;
import org.fusesource.restygwt.client.Json.Style;

import de.mkristian.gwt.rails.views.ExternalApplication;

@Json(style = Style.RAILS)
public class Application implements ExternalApplication {

  private final String name;

  private final String url;

  @JsonCreator
  public Application(@JsonProperty("name") String name, 
          @JsonProperty("url") String url){
    this.url = url;
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public String getUrl(){
    return url;
  }

}
