package com.example.core.service.healthinsurance;

import com.example.api.inputoutput.healthinsurance.late.HealthInsuranceLateOperation;
import com.example.api.inputoutput.healthinsurance.late.HealthInsuranceLateRequest;
import com.example.api.inputoutput.healthinsurance.late.HealthInsuranceLateResponse;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.enums.FormStatus;
import com.example.persistence.entity.insurance.HealthInsuranceLateForm;
import com.example.persistence.repository.insurance.HealthInsuranceLateFormRepository;
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
