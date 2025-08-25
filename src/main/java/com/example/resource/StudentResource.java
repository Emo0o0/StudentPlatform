package com.example.resource;

import com.example.api.inputoutput.student.countbyspecialty.CountStudentsBySpecialtyOperation;
import com.example.api.inputoutput.student.countbyspecialty.CountStudentsBySpecialtyRequest;
import com.example.api.inputoutput.student.countbystatus.CountStudentsByStatusOperation;
import com.example.api.inputoutput.student.countbystatus.CountStudentsByStatusRequest;
import com.example.api.inputoutput.student.countfailed.CountFailedStudentsOperation;
import com.example.api.inputoutput.student.countfailed.CountFailedStudentsRequest;
import com.example.api.inputoutput.student.countnotattendedexam.CountStudentsNotAttendedExamOperation;
import com.example.api.inputoutput.student.countnotattendedexam.CountStudentsNotAttendedExamRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/student")
public class StudentResource {

    @Inject
    CountStudentsBySpecialtyOperation countStudentsBySpecialtyOperation;
    @Inject
    CountStudentsByStatusOperation countStudentsByStatusOperation;
    @Inject
    CountStudentsNotAttendedExamOperation countStudentsNotAttendedExamOperation;
    @Inject
    CountFailedStudentsOperation countFailedStudentsOperation;

    @GET
    @Path("/countBySpecialty")
    public Response countStudentsBySpecialty(@QueryParam("specialty") String specialty) {

        CountStudentsBySpecialtyRequest input = CountStudentsBySpecialtyRequest.builder()
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(countStudentsBySpecialtyOperation.process(input))
                .build();
    }

    @GET
    @Path("/countByStatus")
    public Response countStudentsByStatus(@QueryParam("status") String status,
                                          @QueryParam("faculty") String faculty,
                                          @QueryParam("department") String department,
                                          @QueryParam("specialty") String specialty) {
        CountStudentsByStatusRequest input = CountStudentsByStatusRequest.builder()
                .studentStatus(status)
                .faculty(faculty)
                .department(department)
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(countStudentsByStatusOperation.process(input))
                .build();
    }

    @GET
    @Path("/countNotAttended")
    public Response countStudentsNotAttendedExam(@QueryParam("faculty") String faculty,
                                                 @QueryParam("department") String department,
                                                 @QueryParam("specialty") String specialty) {
        CountStudentsNotAttendedExamRequest input = CountStudentsNotAttendedExamRequest.builder()
                .faculty(faculty)
                .department(department)
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(countStudentsNotAttendedExamOperation.process(input))
                .build();
    }

    @GET
    @Path("countFailed")
    public Response countFailedStudents(@QueryParam("faculty") String faculty,
                                        @QueryParam("department") String department,
                                        @QueryParam("specialty") String specialty) {
        CountFailedStudentsRequest input = CountFailedStudentsRequest.builder()
                .faculty(faculty)
                .department(department)
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(countFailedStudentsOperation.process(input))
                .build();
    }
}
