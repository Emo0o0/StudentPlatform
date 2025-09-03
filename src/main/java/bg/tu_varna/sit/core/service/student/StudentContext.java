package bg.tu_varna.sit.core.service.student;

import bg.tu_varna.sit.core.exception.student.StudentNotFoundException;
import bg.tu_varna.sit.persistence.entity.Student;
import bg.tu_varna.sit.persistence.repository.StudentRepository;
//import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class StudentContext {

    @Inject
    StudentRepository studentRepository;

    @Inject
    SecurityIdentity securityIdentity;

    public Student getCurrentStudent() {
        String facultyNumber = securityIdentity.getPrincipal().getName().substring(1, 9);
        return studentRepository.findByFacultyNumber(facultyNumber)
                .orElseThrow(() -> new StudentNotFoundException("Student with faculty number [" + facultyNumber + "] was not found"));
//        return studentRepository.findByFacultyNumber("12345678")
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//        return null;
    }

}
