package bg.tu_varna.sit.resource;

import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import bg.tu_varna.sit.persistence.repository.MarkRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/mark")
public class MarkResource {


    @Inject
    MarkRepository markRepository;


    @GET
    @Path("/subjectStats")
    public Response getStudentStats(@QueryParam("faculty") String faculty,
                                    @QueryParam("department") String department,
                                    @QueryParam("specialty") String specialty) {

        return Response.status(200)
                .entity(markRepository.getSubjectStatisticsBySpecialty(Faculty.valueOf(faculty), department, specialty))
                .build();
    }

    @GET
    @Path("/studentAverages")
    public Response getStudentAverages(@QueryParam("faculty") String faculty,
                                    @QueryParam("department") String department,
                                    @QueryParam("specialty") String specialty) {

        return Response.status(200)
                .entity(markRepository.getStudentAverages(Faculty.valueOf(faculty), department, specialty))
                .build();
    }

    @GET
    @Path("/specialtyAverages")
    public Response getSpecialtyAverages(@QueryParam("faculty") String faculty,
                                       @QueryParam("department") String department,
                                       @QueryParam("specialty") String specialty) {

        return Response.status(200)
                .entity(markRepository.getSpecialtyAverages(Faculty.valueOf(faculty), department, specialty))
                .build();
    }

}
