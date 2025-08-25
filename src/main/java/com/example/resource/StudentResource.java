package com.example.resource;

import com.example.api.inputoutput.student.countbyspecialty.CountStudentsBySpecialtyOperation;
import com.example.api.inputoutput.student.countbyspecialty.CountStudentsBySpecialtyRequest;
import com.example.api.inputoutput.student.countbystatus.CountStudentsByStatusOperation;
import com.example.api.inputoutput.student.countbystatus.CountStudentsByStatusRequest;
import com.example.persistence.entity.PersonalAcademicInfo;
import com.example.persistence.entity.StudentStatus;
import com.example.persistence.entity.enums.CourseYear;
import com.example.persistence.entity.enums.DegreeLevel;
import com.example.persistence.entity.enums.Faculty;
import com.example.persistence.entity.enums.Semester;
import com.example.persistence.repository.PersonalAcademicInfoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

}
