package com.example.core.service.healthinsurance;

import com.example.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateOperation;
import com.example.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateRequest;
import com.example.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateResponse;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.enums.FormStatus;
import com.example.persistence.entity.insurance.HealthInsuranceTerminateForm;
import com.example.persistence.repository.HealthInsuranceTerminateFormRepository;
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
