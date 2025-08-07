package com.example.resource;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/form/scholarship")
@Authenticated
public class ScholarshipResource {

    @POST
    @Path("/achievment")
    public Response applyForSpecialAchievement(){
        return Response.status(201)
                .entity(null)
                .build();
    }
    @POST
    @Path("/firstyear")
    public Response applyForFirstYear(){
        return Response.status(201)
                .entity(null)
                .build();
    }
    @POST
    @Path("/foreign")
    public Response applyForForeign(){
        return Response.status(201)
                .entity(null)
                .build();
    }
    @POST
    @Path("/merit")
    public Response applyForMerit(){
        return Response.status(201)
                .entity(null)
                .build();
    }
    @POST
    @Path("/meritincome")
    public Response applyForMeritWithIncome(){
        return Response.status(201)
                .entity(null)
                .build();
    }
    @POST
    @Path("/social")
    public Response applyForSocial(){
        return Response.status(201)
                .entity(null)
                .build();
    }

}
