package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate.HealthInsuranceTerminateFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate.HealthInsuranceTerminateFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate.HealthInsuranceTerminateFormUpdateStatusResponse;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceTerminateForm;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceTerminateFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HealthInsuranceTerminateFormUpdateStatusProcessor implements HealthInsuranceTerminateFormUpdateStatusOperation {

    @Inject
    HealthInsuranceTerminateFormRepository healthInsuranceTerminateFormRepository;
    @Override
    @Transactional
    public HealthInsuranceTerminateFormUpdateStatusResponse process(HealthInsuranceTerminateFormUpdateStatusRequest request) {
        HealthInsuranceTerminateForm form = healthInsuranceTerminateFormRepository.findById(Long.valueOf(request.getFormId()));
        form.updateStatus(FormStatus.valueOf(request.getFormStatus()));

        return HealthInsuranceTerminateFormUpdateStatusResponse.builder()
                .success(true)
                .build();
    }
}
