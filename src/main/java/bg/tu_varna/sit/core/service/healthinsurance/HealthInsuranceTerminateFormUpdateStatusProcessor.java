package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate.HealthInsuranceTerminateFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate.HealthInsuranceTerminateFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate.HealthInsuranceTerminateFormUpdateStatusResponse;
import bg.tu_varna.sit.core.exception.InvalidEnumValueException;
import bg.tu_varna.sit.core.exception.healthinsurance.HealthInsuranceTerminateFormIdNotFoundException;
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
        HealthInsuranceTerminateForm form = healthInsuranceTerminateFormRepository.findByIdOptional(Long.valueOf(request.getFormId()))
                .orElseThrow(()-> new HealthInsuranceTerminateFormIdNotFoundException("Health insurance terminate form with id " + request.getFormId() + " not found!"));

        try {
            form.updateStatus(FormStatus.valueOf(request.getFormStatus()));
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid status value: " + request.getFormStatus());
        }

        return HealthInsuranceTerminateFormUpdateStatusResponse.builder()
                .success(true)
                .build();
    }
}
