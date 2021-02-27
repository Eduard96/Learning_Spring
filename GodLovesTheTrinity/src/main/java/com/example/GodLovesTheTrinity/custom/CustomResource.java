package com.example.GodLovesTheTrinity.custom;

import com.example.GodLovesTheTrinity.TransactionInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/custom/*")
public class CustomResource {

    @POST
    //@Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response customGet(String trs_info) {
        TransactionInfo trs = new Gson().fromJson(trs_info, TransactionInfo.class);
        System.out.println(new Gson().toJson(trs));
        return Response.ok().entity(new Gson().toJson(trs)).build();
    }
}
