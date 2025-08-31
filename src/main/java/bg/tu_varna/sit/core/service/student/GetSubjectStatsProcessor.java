package bg.tu_varna.sit.core.service.student;

import bg.tu_varna.sit.api.inputoutput.student.SubjectStatsDTO;
import bg.tu_varna.sit.api.inputoutput.student.getsubjectstats.GetSubjectStatsOperation;
import bg.tu_varna.sit.api.inputoutput.student.getsubjectstats.GetSubjectStatsRequest;
import bg.tu_varna.sit.api.inputoutput.student.getsubjectstats.GetSubjectStatsResponse;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import bg.tu_varna.sit.persistence.repository.MarkRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetSubjectStatsProcessor implements GetSubjectStatsOperation {

    @Inject
    MarkRepository markRepository;
    @Override
    public GetSubjectStatsResponse process(GetSubjectStatsRequest request) {

        Faculty faculty = request.getFaculty() == null ? null : Faculty.valueOf(request.getFaculty());

        List<SubjectStatsDTO> subjectStats = markRepository.getSubjectStatisticsBySpecialty(faculty, request.getDepartment(), request.getSpecialty());

        return GetSubjectStatsResponse.builder()
                .subjectStats(subjectStats)
                .build();
    }
}
