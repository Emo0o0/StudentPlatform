package bg.tu_varna.sit.resource.config;


import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.Student;
import bg.tu_varna.sit.persistence.entity.StudentStatus;
import bg.tu_varna.sit.persistence.entity.enums.CourseYear;
import bg.tu_varna.sit.persistence.entity.enums.DegreeLevel;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import bg.tu_varna.sit.persistence.entity.enums.Semester;
import bg.tu_varna.sit.persistence.repository.StudentRepository;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.time.Year;

@Provider
@Priority(Priorities.USER)
@RequestScoped
public class StudentPersistenceFilter implements ContainerRequestFilter {

    @Inject
    JsonWebToken jwt;
    @Inject
    StudentRepository studentRepository;


    @Override
    @Transactional
    public void filter(ContainerRequestContext containerRequestContext) {
        if (jwt == null || !jwt.getClaimNames().contains("email")) {
            return;
        }

        String nameClaim = jwt.getClaim("name");
        String studentEmail = jwt.getClaim("email");
        String studentFirstName = nameClaim.split(" ")[0];
        String studentLastName = nameClaim.split(" ")[3];
        String studentName = nameClaim.split(" ")[0] + " " + nameClaim.split(" ")[3];
        String studentFacultyNumber = nameClaim.split(" ")[2];
        String studentSpecialty = nameClaim.split(" ")[1];

        if (studentRepository.findByEmail(studentEmail) == null) {

            PersonalAcademicInfo personalAcademicInfo=PersonalAcademicInfo.builder()
                    .email(studentEmail)
                    .firstName(studentFirstName)
                    .secondName("Emo")
                    .lastName(studentLastName)
                    .egn("8329401387")
                    .address("Street")
                    .phoneNumber("8318412847")
                    .placeOfResidence("Varna")
                    .streetName("Dragoman")
                    .streetNumber(15)
                    .entrance("B")
                    .floor(3)
                    .flatNumber(7)
                    .facultyNumber(studentFacultyNumber)
                    .courseYear(CourseYear.III)
                    .semester(Semester.SIXTH)
                    .degreeLevel(DegreeLevel.BACHELOR)
                    .studentStatus(StudentStatus.ACTIVE)
                    .faculty(Faculty.COMPUTER_AUTOMATION)
                    .department("SIT")
                    .specialty(studentSpecialty)
                    .studentGroup(3)
                    .subGroup('a')
                    .build();

            Student student = Student.builder()
                    .personalAcademicInfo(personalAcademicInfo)
                    .build();

            studentRepository.persist(student);
        }
    }
}
