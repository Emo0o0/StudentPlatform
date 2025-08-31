package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateapply.HealthInsuranceApplyFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateapply.HealthInsuranceApplyFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateapply.HealthInsuranceApplyFormUpdateStatusResponse;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceApplyForm;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceApplyFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HealthInsuranceApplyFormUpdateStatusProcessor implements HealthInsuranceApplyFormUpdateStatusOperation {

    @Inject
    HealthInsuranceApplyFormRepository healthInsuranceApplyFormRepository;

    @Override
    @Transactional
    public HealthInsuranceApplyFormUpdateStatusResponse process(HealthInsuranceApplyFormUpdateStatusRequest request) {

        HealthInsuranceApplyForm form = healthInsuranceApplyFormRepository.findById(Long.valueOf(request.getFormId()));
        form.updateStatus(FormStatus.valueOf(request.getFormStatus()));

        return HealthInsuranceApplyFormUpdateStatusResponse.builder()
                .success(true)
                .build();
    }
}
