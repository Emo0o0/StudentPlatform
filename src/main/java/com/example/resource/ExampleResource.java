package com.example.resource;

import com.example.persistence.entity.Student;
import com.example.persistence.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.io.IOException;
import java.util.Optional;

@Path("/api")
@Authenticated
public class ExampleResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    SecurityIdentity identity;

    @Inject
    StudentRepository studentRepository;

    @GET
    @Path("/debug")
    @Produces(MediaType.APPLICATION_JSON)
    public Response debugRoles() throws JsonProcessingException {
        Optional<Student> optionalStudent = studentRepository.findByFacultyNumber(identity.getPrincipal().getName().substring(1, 9));

        if (optionalStudent.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Student not found for faculty number: " + identity.getPrincipal().getName().substring(1, 9))
                    .build();
        }

        Student student = optionalStudent.get();
        System.out.println("DEBUG JSON: " + new ObjectMapper().writeValueAsString(student));

        return Response.ok(student)
                .build();
    }

    @GET
    @Path("/roles")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response getRoles() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(mapper.writeValueAsString(jwt.getClaim("resource_access")));
        JsonNode mainClientRoles = root.path("MainClient").path("roles");

        for (JsonNode role : mainClientRoles) {
            String roleName = role.path("string").asText();
            System.out.println(roleName);
        }


        return Response.status(200)
                .entity(jwt.getClaim("resource_access"))
                .build();
    }

    @GET
    @Path("/jwtGroups")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response getJwtGroups() {
        return Response.ok(jwt.getGroups()).build();
    }

    @GET
    @Path("/student")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllClaims() {
        StringBuilder sb = new StringBuilder();
        for (String name : jwt.getClaimNames()) {
            sb.append(name)
                    .append(" - ")
                    .append(jwt.getClaim(name).toString())
                    .append("\n");
        }
        sb.append("Groups: ").append(jwt.getGroups());
        return sb.toString();
    }

    @GET
    @Path("/realAdmin")
    @RolesAllowed("realmadmin")
    @Produces(MediaType.TEXT_PLAIN)
    public String testRealmAdmin() {
        return "IT WORKED";
    }

    @GET
    @Path("/admin")
    @RolesAllowed("admin")
    @Produces(MediaType.TEXT_PLAIN)
    public String testAdmin() {
        return "IT WORKED";
    }


}

