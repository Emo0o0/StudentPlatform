package bg.tu_varna.sit.core.service.student;

import bg.tu_varna.sit.api.inputoutput.student.StudentAverageDTO;
import bg.tu_varna.sit.api.inputoutput.student.getaverages.GetStudentAveragesOperation;
import bg.tu_varna.sit.api.inputoutput.student.getaverages.GetStudentAveragesRequest;
import bg.tu_varna.sit.api.inputoutput.student.getaverages.GetStudentAveragesResponse;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import bg.tu_varna.sit.persistence.repository.MarkRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetStudentAveragesProcessor implements GetStudentAveragesOperation {

    @Inject
    MarkRepository markRepository;

    @Override
    public GetStudentAveragesResponse process(GetStudentAveragesRequest request) {

        Faculty faculty = request.getFaculty() == null ? null : Faculty.valueOf(request.getFaculty());

        List<StudentAverageDTO> studentAverage = markRepository.getStudentAverages(faculty, request.getDepartment(), request.getSpecialty());

        return GetStudentAveragesResponse.builder()
                .studentAverages(studentAverage)
                .build();
    }
}
