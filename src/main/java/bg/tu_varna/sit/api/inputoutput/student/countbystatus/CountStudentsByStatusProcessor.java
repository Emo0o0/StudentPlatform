package bg.tu_varna.sit.api.inputoutput.student.countbystatus;

import bg.tu_varna.sit.persistence.entity.StudentStatus;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import bg.tu_varna.sit.persistence.repository.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CountStudentsByStatusProcessor implements CountStudentsByStatusOperation {

    @Inject
    StudentRepository studentRepository;

    @Override
    public CountStudentsByStatusResponse process(CountStudentsByStatusRequest request) {

        long students = studentRepository.countByStatus(StudentStatus.valueOf(request.getStudentStatus()),
                Faculty.valueOf(request.getFaculty()),
                request.getDepartment(),
                request.getSpecialty());

        return CountStudentsByStatusResponse.builder()
                .count(students)
                .build();
    }
}
