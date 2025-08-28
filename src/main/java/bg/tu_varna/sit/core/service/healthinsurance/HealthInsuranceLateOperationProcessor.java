package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateResponse;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceLateForm;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceLateFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HealthInsuranceLateOperationProcessor implements HealthInsuranceLateOperation {

    @Inject
    HealthInsuranceLateFormRepository healthInsuranceLateFormRepository;
    @Inject
    StudentContext studentContext;

    @Override
    @Transactional
    public HealthInsuranceLateResponse process(HealthInsuranceLateRequest request) {

        HealthInsuranceLateForm healthInsuranceLateForm = HealthInsuranceLateForm.builder()
                .student(studentContext.getCurrentStudent())
                .schoolYear(request.getSchoolYear())
                .formStatus(FormStatus.SENT)
                .build();

        healthInsuranceLateFormRepository.persist(healthInsuranceLateForm);

        return HealthInsuranceLateResponse.builder()
                .success(true)
                .build();
    }
}
