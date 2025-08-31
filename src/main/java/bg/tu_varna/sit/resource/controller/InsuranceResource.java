package bg.tu_varna.sit.resource.controller;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateapply.HealthInsuranceApplyFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateapply.HealthInsuranceApplyFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate.HealthInsuranceLateFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate.HealthInsuranceLateFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate.HealthInsuranceTerminateFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate.HealthInsuranceTerminateFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.apply.StudentGetInsuranceApplyFormsRequest;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.apply.StudentGetInsuranceFormsOperation;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.late.StudentGetInsuranceLateFormsOperation;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.late.StudentGetInsuranceLateFormsRequest;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.terminate.StudentGetInsuranceTerminateFormsOperation;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.terminate.StudentGetInsuranceTerminateFormsRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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
    @Inject
    StudentGetInsuranceFormsOperation studentGetInsuranceFormsOperation;
    @Inject
    StudentGetInsuranceLateFormsOperation studentGetInsuranceLateFormsOperation;
    @Inject
    StudentGetInsuranceTerminateFormsOperation studentGetInsuranceTerminateFormsOperation;
    @Inject
    HealthInsuranceApplyFormUpdateStatusOperation healthInsuranceApplyFormUpdateStatusOperation;
    @Inject
    HealthInsuranceLateFormUpdateStatusOperation healthInsuranceLateFormUpdateStatusOperation;
    @Inject
    HealthInsuranceTerminateFormUpdateStatusOperation healthInsuranceTerminateFormUpdateStatusOperation;

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

    @GET
    @Path("/apply")
    public Response getInsuranceApplyForms(@QueryParam("studentId") String studentId,
                                           @QueryParam("specialty") String specialty) {


        StudentGetInsuranceApplyFormsRequest input = StudentGetInsuranceApplyFormsRequest.builder()
                .studentId(studentId)
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(studentGetInsuranceFormsOperation.process(input))
                .build();
    }

    @GET
    @Path("/late")
    public Response getInsuranceLateForms(@QueryParam("studentId") String studentId,
                                          @QueryParam("specialty") String specialty) {

        StudentGetInsuranceLateFormsRequest input = StudentGetInsuranceLateFormsRequest.builder()
                .studentId(studentId)
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(studentGetInsuranceLateFormsOperation.process(input))
                .build();
    }

    @GET
    @Path("/terminate")
    public Response getInsuranceTerminateForms(@QueryParam("studentId") String studentId,
                                               @QueryParam("specialty") String specialty) {

        StudentGetInsuranceTerminateFormsRequest input = StudentGetInsuranceTerminateFormsRequest.builder()
                .studentId(studentId)
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(studentGetInsuranceTerminateFormsOperation.process(input))
                .build();
    }

    @PUT
    @Path("/apply/update")
    public Response updateApplyFormStatus(@QueryParam("formId") String formId,
                                          @QueryParam("status") String status) {
        HealthInsuranceApplyFormUpdateStatusRequest input = HealthInsuranceApplyFormUpdateStatusRequest.builder()
                .formId(formId)
                .formStatus(status)
                .build();

        return Response.status(200)
                .entity(healthInsuranceApplyFormUpdateStatusOperation.process(input))
                .build();
    }
    @PUT
    @Path("/late/update")
    public Response updateLateFormStatus(@QueryParam("formId") String formId,
                                          @QueryParam("status") String status) {
        HealthInsuranceLateFormUpdateStatusRequest input = HealthInsuranceLateFormUpdateStatusRequest.builder()
                .formId(formId)
                .formStatus(status)
                .build();

        return Response.status(200)
                .entity(healthInsuranceLateFormUpdateStatusOperation.process(input))
                .build();
    }
    @PUT
    @Path("/terminate/update")
    public Response updateTerminateFormStatus(@QueryParam("formId") String formId,
                                          @QueryParam("status") String status) {
        HealthInsuranceTerminateFormUpdateStatusRequest input = HealthInsuranceTerminateFormUpdateStatusRequest.builder()
                .formId(formId)
                .formStatus(status)
                .build();

        return Response.status(200)
                .entity(healthInsuranceTerminateFormUpdateStatusOperation.process(input))
                .build();
    }

}
