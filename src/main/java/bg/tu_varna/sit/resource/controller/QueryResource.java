package bg.tu_varna.sit.resource.controller;

import bg.tu_varna.sit.api.inputoutput.student.getaverages.GetStudentAveragesOperation;
import bg.tu_varna.sit.api.inputoutput.student.getaverages.GetStudentAveragesRequest;
import bg.tu_varna.sit.api.inputoutput.student.getspecialtyaverages.GetSpecialtyAveragesOperation;
import bg.tu_varna.sit.api.inputoutput.student.getspecialtyaverages.GetSpecialtyAveragesRequest;
import bg.tu_varna.sit.api.inputoutput.student.getstats.GetStudentStatsOperation;
import bg.tu_varna.sit.api.inputoutput.student.getstats.GetStudentStatsRequest;
import bg.tu_varna.sit.api.inputoutput.student.getsubjectstats.GetSubjectStatsOperation;
import bg.tu_varna.sit.api.inputoutput.student.getsubjectstats.GetSubjectStatsRequest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/query")
public class QueryResource {

    @Inject
    GetStudentStatsOperation getStudentStatsOperation;
    @Inject
    GetSubjectStatsOperation getSubjectStatsOperation;
    @Inject
    GetStudentAveragesOperation getStudentAveragesOperation;
    @Inject
    GetSpecialtyAveragesOperation getSpecialtyAveragesOperation;


    @GET
    @Path("/studentStats")
    public Response getStudentStats(@QueryParam("faculty") String faculty,
                                    @QueryParam("department") String department,
                                    @QueryParam("specialty") String specialty
    ) {
        GetStudentStatsRequest input = GetStudentStatsRequest.builder()
                .faculty(faculty)
                .department(department)
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(getStudentStatsOperation.process(input))
                .build();
    }

    @GET
    @Path("/subjectStats")
    public Response getSubjectStats(@QueryParam("faculty") String faculty,
                                    @QueryParam("department") String department,
                                    @QueryParam("specialty") String specialty) {

        GetSubjectStatsRequest input = GetSubjectStatsRequest.builder()
                .faculty(faculty)
                .department(department)
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(getSubjectStatsOperation.process(input))
                .build();
    }

    @GET
    @Path("/studentAverages")
    public Response getStudentAverages(@QueryParam("faculty") String faculty,
                                       @QueryParam("department") String department,
                                       @QueryParam("specialty") String specialty) {

        GetStudentAveragesRequest input = GetStudentAveragesRequest.builder()
                .faculty(faculty)
                .department(department)
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(getStudentAveragesOperation.process(input))
                .build();
    }

    @GET
    @Path("/specialtyAverages")
    public Response getSpecialtyAverages(@QueryParam("specialty") String specialty) {

        GetSpecialtyAveragesRequest input = GetSpecialtyAveragesRequest.builder()
                .specialty(specialty)
                .build();

        return Response.status(200)
                .entity(getSpecialtyAveragesOperation.process(input))
                .build();
    }

}
