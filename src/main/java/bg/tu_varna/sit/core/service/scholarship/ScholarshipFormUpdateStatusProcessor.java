package bg.tu_varna.sit.core.service.scholarship;

import bg.tu_varna.sit.api.inputoutput.scholarship.updatestatus.ScholarshipFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.updatestatus.ScholarshipFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.updatestatus.ScholarshipFormUpdateStatusResponse;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipApplyForm;
import bg.tu_varna.sit.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ScholarshipFormUpdateStatusProcessor implements ScholarshipFormUpdateStatusOperation {

    @Inject
    ScholarshipApplyFormRepository scholarshipApplyFormRepository;
    @Override
    @Transactional
    public ScholarshipFormUpdateStatusResponse process(ScholarshipFormUpdateStatusRequest request) {
        ScholarshipApplyForm form = scholarshipApplyFormRepository.findById(Long.valueOf(request.getFormId()));
        form.updateStatus(FormStatus.valueOf(request.getFormStatus()));

        return ScholarshipFormUpdateStatusResponse.builder()
                .success(true)
                .build();
    }
}
