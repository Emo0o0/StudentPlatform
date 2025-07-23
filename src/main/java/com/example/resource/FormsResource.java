package com.example.resource;

import com.example.api.inputoutput.dormitory.apply.DormitoryApplyOperation;
import com.example.api.inputoutput.dormitory.apply.DormitoryApplyRequest;
import com.example.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomOperation;
import com.example.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomRequest;
import com.example.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyOperation;
import com.example.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyRequest;
import com.example.api.inputoutput.healthinsurance.late.HealthInsuranceLateOperation;
import com.example.api.inputoutput.healthinsurance.late.HealthInsuranceLateRequest;
import com.example.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateOperation;
import com.example.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateRequest;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/form")
@Authenticated
public class FormsResource {

    @Inject
    HealthInsuranceApplyOperation healthInsuranceApplyOperation;
    @Inject
    HealthInsuranceLateOperation healthInsuranceLateOperation;
    @Inject
    HealthInsuranceTerminateOperation healthInsuranceTerminateOperation;
    @Inject
    DormitoryApplyOperation dormitoryApplyOperation;
    @Inject
    DormitoryKeepRoomOperation dormitoryKeepRoomOperation;

    @POST
    @Path("/healthInsurance/apply")
    public Response applyForHealthInsurance(HealthInsuranceApplyRequest input) {
        return Response.status(201)
                .entity(healthInsuranceApplyOperation.process(input))
                .build();
    }

    @POST
    @Path("/healthInsurance/late")
    public Response applyForLateHealthInsurance(HealthInsuranceLateRequest input) {
        return Response.status(201)
                .entity(healthInsuranceLateOperation.process(input))
                .build();
    }

    @POST
    @Path("/healthInsurance/terminate")
    public Response terminateHealthInsurance(HealthInsuranceTerminateRequest input) {
        return Response.status(201)
                .entity(healthInsuranceTerminateOperation.process(input))
                .build();
    }

    @POST
    @Path("/dormitory/apply")
    public Response applyForDormitory(DormitoryApplyRequest input) {
        return Response.status(201)
                .entity(dormitoryApplyOperation.process(input))
                .build();
    }

    @POST
    @Path("/dormitory/keepRoom")
    public Response requestToKeepRoom(DormitoryKeepRoomRequest input) {
        return Response.status(201)
                .entity(dormitoryKeepRoomOperation.process(input))
                .build();
    }


}
