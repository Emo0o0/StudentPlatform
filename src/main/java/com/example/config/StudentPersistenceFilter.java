//package com.example.config;
//
//import com.example.persistence.entity.Student;
//import com.example.persistence.repository.StudentRepository;
//import jakarta.annotation.Priority;
//import jakarta.enterprise.context.RequestScoped;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import jakarta.ws.rs.Priorities;
//import jakarta.ws.rs.container.ContainerRequestContext;
//import jakarta.ws.rs.container.ContainerRequestFilter;
//import jakarta.ws.rs.ext.Provider;
//import org.eclipse.microprofile.jwt.JsonWebToken;
//
//import java.io.IOException;
//import java.time.Year;
//
//@Provider
//@Priority(Priorities.USER)
//@RequestScoped
//public class StudentPersistenceFilter implements ContainerRequestFilter {
//
//    @Inject
//    JsonWebToken jwt;
//    @Inject
//    StudentRepository studentRepository;
//
//
//    @Override
//    @Transactional
//    public void filter(ContainerRequestContext containerRequestContext) {
//        if (jwt == null || !jwt.getClaimNames().contains("email")) {
//            return;
//        }
//
//        String nameClaim = jwt.getClaim("name");
//        String studentEmail = jwt.getClaim("email");
//        String studentName = nameClaim.split(" ")[0] + " " + nameClaim.split(" ")[3];
//        String studentFacultyNumber = nameClaim.split(" ")[2];
//        String studentSpecialty = nameClaim.split(" ")[1];
//        int currentYear = Year.now().getValue();
//
////        if (studentRepository.findByEmail(studentEmail) == null) {
////            Student student = Student.builder()
////                    .fullName(studentName)
////                    .email(studentEmail)
////                    .facultyNumber(studentFacultyNumber)
////                    .specialty(studentSpecialty)
////                    .groupNumber(1)
////                    .subGroup('a')
////                    .academicYear(currentYear + "/" + currentYear + 1)
////                    .build();
////
////            studentRepository.persist(student);
////        }
//    }
//}
