package bg.tu_varna.sit.core.service.student;

import bg.tu_varna.sit.api.inputoutput.student.SpecialtyAverageDTO;
import bg.tu_varna.sit.api.inputoutput.student.getspecialtyaverages.GetSpecialtyAveragesOperation;
import bg.tu_varna.sit.api.inputoutput.student.getspecialtyaverages.GetSpecialtyAveragesRequest;
import bg.tu_varna.sit.api.inputoutput.student.getspecialtyaverages.GetSpecialtyAveragesResponse;
import bg.tu_varna.sit.persistence.repository.MarkRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetSpecialtyAveragesProcessor implements GetSpecialtyAveragesOperation {

    @Inject
    MarkRepository markRepository;

    @Override
    public GetSpecialtyAveragesResponse process(GetSpecialtyAveragesRequest request) {

        List<SpecialtyAverageDTO> specialtyAverage = markRepository.getSpecialtyAverages(request.getSpecialty());

        return GetSpecialtyAveragesResponse.builder()
                .specialtyAverages(specialtyAverage)
                .build();
    }
}
