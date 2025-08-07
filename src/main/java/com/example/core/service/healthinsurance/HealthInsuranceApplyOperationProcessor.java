package com.example.core.service.healthinsurance;

import com.example.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyOperation;
import com.example.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyRequest;
import com.example.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyResponse;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.enums.FormStatus;
import com.example.persistence.entity.insurance.HealthInsuranceApplyForm;
import com.example.persistence.repository.insurance.HealthInsuranceApplyFormRepository;
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
