package com.example.resource;

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

@Path("/form/healthInsurance")
//@Authenticated
public class InsuranceResource {

    @Inject
    HealthInsuranceApplyOperation healthInsuranceApplyOperation;
    @Inject
    HealthInsuranceLateOperation healthInsuranceLateOperation;
    @Inject
    HealthInsuranceTerminateOperation healthInsuranceTerminateOperation;

    @POST
    @Path("/apply")
    public Response applyForHealthInsurance(HealthInsuranceApplyRequest input) {
        return Response.status(201)
                .entity(healthInsuranceApplyOperation.process(input))
                .build();
    }

    @POST
    @Path("/late")
    public Response applyForLateHealthInsurance(HealthInsuranceLateRequest input) {
        return Response.status(201)
                .entity(healthInsuranceLateOperation.process(input))
                .build();
    }

    @POST
    @Path("/terminate")
    public Response terminateHealthInsurance(HealthInsuranceTerminateRequest input) {
        return Response.status(201)
                .entity(healthInsuranceTerminateOperation.process(input))
                .build();
    }
}
