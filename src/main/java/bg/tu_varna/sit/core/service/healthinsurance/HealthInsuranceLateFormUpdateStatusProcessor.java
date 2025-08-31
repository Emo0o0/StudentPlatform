package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate.HealthInsuranceLateFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate.HealthInsuranceLateFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate.HealthInsuranceLateFormUpdateStatusResponse;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceLateForm;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceLateFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HealthInsuranceLateFormUpdateStatusProcessor implements HealthInsuranceLateFormUpdateStatusOperation {

    @Inject
    HealthInsuranceLateFormRepository healthInsuranceLateFormRepository;
    @Override
    @Transactional
    public HealthInsuranceLateFormUpdateStatusResponse process(HealthInsuranceLateFormUpdateStatusRequest request) {
        HealthInsuranceLateForm form = healthInsuranceLateFormRepository.findById(Long.valueOf(request.getFormId()));
        form.updateStatus(FormStatus.valueOf(request.getFormStatus()));

        return HealthInsuranceLateFormUpdateStatusResponse.builder()
                .success(true)
                .build();
    }
}
