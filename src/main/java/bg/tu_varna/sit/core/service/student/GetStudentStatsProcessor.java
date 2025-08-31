package bg.tu_varna.sit.core.service.student;

import bg.tu_varna.sit.api.inputoutput.student.StudentStatsDTO;
import bg.tu_varna.sit.api.inputoutput.student.getstats.GetStudentStatsOperation;
import bg.tu_varna.sit.api.inputoutput.student.getstats.GetStudentStatsRequest;
import bg.tu_varna.sit.api.inputoutput.student.getstats.GetStudentStatsResponse;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import bg.tu_varna.sit.persistence.repository.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetStudentStatsProcessor implements GetStudentStatsOperation {

    @Inject
    StudentRepository studentRepository;

    @Override
    public GetStudentStatsResponse process(GetStudentStatsRequest request) {

        Faculty faculty = request.getFaculty() == null ? null : Faculty.valueOf(request.getFaculty());

        List<StudentStatsDTO> studentStats = studentRepository.getStudentStatistics(faculty, request.getDepartment(), request.getSpecialty());


        return GetStudentStatsResponse.builder()
                .studentStats(studentStats)
                .build();
    }
}
