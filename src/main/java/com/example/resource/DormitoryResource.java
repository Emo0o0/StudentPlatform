package com.example.resource;

import com.example.api.inputoutput.dormitory.apply.DormitoryApplyOperation;
import com.example.api.inputoutput.dormitory.apply.DormitoryApplyRequest;
import com.example.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomOperation;
import com.example.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomRequest;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/form/dormitory")
@Authenticated
public class DormitoryResource {

    @Inject
    DormitoryApplyOperation dormitoryApplyOperation;
    @Inject
    DormitoryKeepRoomOperation dormitoryKeepRoomOperation;


    @POST
    @Path("/apply")
    public Response applyForDormitory(DormitoryApplyRequest input) {
        return Response.status(201)
                .entity(dormitoryApplyOperation.process(input))
                .build();
    }

    @POST
    @Path("/keepRoom")
    public Response requestToKeepRoom(DormitoryKeepRoomRequest input) {
        return Response.status(201)
                .entity(dormitoryKeepRoomOperation.process(input))
                .build();
    }

}
