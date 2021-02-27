package com.example.GodLovesTheTrinity;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {
    @POST
    @Path("get")
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}