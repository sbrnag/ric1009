package com.ric.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("isAlive")
public class IsAliveResource {

    /**
     * Method handling HTTP GET requests to check the app is live or not. 
     *
     * @return String that will be returned as a text/plain response telling app is alive.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String isAlive() {
        return "App is live and kicking.....";
    }
}
