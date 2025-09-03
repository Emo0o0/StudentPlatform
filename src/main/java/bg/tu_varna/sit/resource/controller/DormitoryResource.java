package bg.tu_varna.sit.resource.controller;

import bg.tu_varna.sit.api.inputoutput.dormitory.apply.DormitoryApplyOperation;
import bg.tu_varna.sit.api.inputoutput.dormitory.apply.DormitoryApplyRequest;
import bg.tu_varna.sit.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomOperation;
import bg.tu_varna.sit.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomRequest;
import bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus.DormitoryFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus.DormitoryFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.student.getdormitoryforms.StudentGetDormitoryApplyFormsOperation;
import bg.tu_varna.sit.api.inputoutput.student.getdormitoryforms.StudentGetDormitoryApplyFormsRequest;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/form/dormitory")
@Authenticated
public class DormitoryResource {

    @Inject
    DormitoryApplyOperation dormitoryApplyOperation;
    @Inject
    DormitoryKeepRoomOperation dormitoryKeepRoomOperation;
    @Inject
    StudentGetDormitoryApplyFormsOperation studentGetDormitoryApplyFormsOperation;
    @Inject
    DormitoryFormUpdateStatusOperation dormitoryFormUpdateStatusOperation;


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

    @GET
    @Path("/apply")
    public Response getDormitoryApplyForms(@QueryParam("studentId") String studentId,
                                           @QueryParam("specialty") String specialty) {
        StudentGetDormitoryApplyFormsRequest input = StudentGetDormitoryApplyFormsRequest.builder()
                .studentId(studentId)
                .specialty(specialty)
                .build();
        return Response.status(200)
                .entity(studentGetDormitoryApplyFormsOperation.process(input))
                .build();
    }

    @PUT
    @Path("/update")
    public Response updateFormStatus(@QueryParam("formId") String formId,
                                     @QueryParam("status") String status) {
        DormitoryFormUpdateStatusRequest input = DormitoryFormUpdateStatusRequest.builder()
                .formId(formId)
                .status(status)
                .build();
        return Response.status(200)
                .entity(dormitoryFormUpdateStatusOperation.process(input))
                .build();
    }

}
