package bg.tu_varna.sit.api.inputoutput.student.countfailed;

import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import bg.tu_varna.sit.persistence.repository.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CountFailedStudentsProcessor implements CountFailedStudentsOperation {

    @Inject
    StudentRepository studentRepository;

    @Override
    public CountFailedStudentsResponse process(CountFailedStudentsRequest request) {

        long students = studentRepository.countWithFail(Faculty.valueOf(request.getFaculty()), request.getDepartment(), request.getSpecialty());

        return CountFailedStudentsResponse.builder()
                .count(students)
                .build();
    }
}
