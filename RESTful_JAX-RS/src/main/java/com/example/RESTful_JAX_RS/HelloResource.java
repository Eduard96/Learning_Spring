package com.example.RESTful_JAX_RS;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/mmm")
    public Response response(){
        return Response.ok().entity("Hi").build();
    }
}