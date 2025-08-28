package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateResponse;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceTerminateForm;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceTerminateFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HealthInsuranceTerminateOperationProcessor implements HealthInsuranceTerminateOperation {

    @Inject
    HealthInsuranceTerminateFormRepository healthInsuranceTerminateFormRepository;
    @Inject
    StudentContext studentContext;

    @Override
    @Transactional
    public HealthInsuranceTerminateResponse process(HealthInsuranceTerminateRequest request) {

        HealthInsuranceTerminateForm healthInsuranceTerminateForm = HealthInsuranceTerminateForm.builder()
                .schoolYear(request.getSchoolYear())
                .terminationReason(request.getTerminationReason())
                .student(studentContext.getCurrentStudent())
                .formStatus(FormStatus.SENT)
                .build();

        healthInsuranceTerminateFormRepository.persist(healthInsuranceTerminateForm);

        return HealthInsuranceTerminateResponse.builder()
                .success(true)
                .build();

    }
}
