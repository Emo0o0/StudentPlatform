package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyResponse;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceApplyForm;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyRequest;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceApplyFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HealthInsuranceApplyOperationProcessor implements HealthInsuranceApplyOperation {

    @Inject
    HealthInsuranceApplyFormRepository healthInsuranceApplyFormRepository;
    @Inject
    StudentContext studentContext;

    @Override
    @Transactional
    public HealthInsuranceApplyResponse process(HealthInsuranceApplyRequest request) {

        HealthInsuranceApplyForm healthInsuranceApplyForm = HealthInsuranceApplyForm.builder()
                .isReceivingWorkRelatedIncome(request.getIsReceivingWorkRelatedIncome())
                .isReceivingPension(request.getIsReceivingPension())
                .isReceivingOtherInsuredIncome(request.getIsReceivingOtherInsuredIncome())
                .currentInsurer(request.getCurrentInsurer())
                .student(studentContext.getCurrentStudent())
                .formStatus(FormStatus.SENT)
                .build();

        healthInsuranceApplyFormRepository.persist(healthInsuranceApplyForm);

        return HealthInsuranceApplyResponse.builder()
                .success(true)
                .build();
    }
}
