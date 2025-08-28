package bg.tu_varna.sit.api.inputoutput.student.countbyspecialty;

import bg.tu_varna.sit.persistence.repository.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CountStudentsBySpecialtyProcessor implements CountStudentsBySpecialtyOperation {

    @Inject
    StudentRepository studentRepository;

    @Override
    @Transactional
    public CountStudentsBySpecialtyResponse process(CountStudentsBySpecialtyRequest request) {

        long students = studentRepository.countBySpecialty(request.getSpecialty());

        return CountStudentsBySpecialtyResponse.builder()
                .count(students)
                .build();
    }
}
