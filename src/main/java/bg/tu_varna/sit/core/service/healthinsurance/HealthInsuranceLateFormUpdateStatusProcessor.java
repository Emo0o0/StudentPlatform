package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate.HealthInsuranceLateFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate.HealthInsuranceLateFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate.HealthInsuranceLateFormUpdateStatusResponse;
import bg.tu_varna.sit.core.exception.InvalidEnumValueException;
import bg.tu_varna.sit.core.exception.healthinsurance.HealthInsuranceLateFormIdNotFoundException;
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

        HealthInsuranceLateForm form = healthInsuranceLateFormRepository.findByIdOptional(Long.valueOf(request.getFormId()))
                .orElseThrow(()-> new HealthInsuranceLateFormIdNotFoundException("Health insurance late form with id " + request.getFormId() + " not found!"));

        try {
            form.updateStatus(FormStatus.valueOf(request.getFormStatus()));
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid status value: " + request.getFormStatus());
        }

        return HealthInsuranceLateFormUpdateStatusResponse.builder()
                .success(true)
                .build();
    }
}
