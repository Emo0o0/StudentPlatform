package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.apply.StudentGetInsuranceApplyFormsListResponse;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.apply.StudentGetInsuranceApplyFormsRequest;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.apply.StudentGetInsuranceApplyFormsResponse;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.apply.StudentGetInsuranceFormsOperation;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceApplyForm;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceApplyFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudentGetInsuranceFormsProcessor implements StudentGetInsuranceFormsOperation {

    @Inject
    HealthInsuranceApplyFormRepository healthInsuranceApplyFormRepository;

    @Override
    public StudentGetInsuranceApplyFormsListResponse process(StudentGetInsuranceApplyFormsRequest request) {

        Long studentId = request.getStudentId()==null ? null : Long.valueOf(request.getStudentId());

        List<HealthInsuranceApplyForm> insuranceApplyForms = healthInsuranceApplyFormRepository.getStudentForms(studentId, request.getSpecialty());

        return StudentGetInsuranceApplyFormsListResponse.builder()
                .forms(insuranceApplyForms.stream()
                        .map(form -> StudentGetInsuranceApplyFormsResponse.builder()
                                .formId(form.getFormId().toString())
                                .studentFirstName(form.getPersonalAcademicInfo().getFirstName())
                                .studentLastName(form.getPersonalAcademicInfo().getLastName())
                                .studentFacultyNumber(form.getPersonalAcademicInfo().getFacultyNumber())
                                .isReceivingWorkRelatedIncome(form.getIsReceivingWorkRelatedIncome())
                                .isReceivingPension(form.getIsReceivingPension())
                                .isReceivingOtherInsuredIncome(form.getIsReceivingOtherInsuredIncome())
                                .currentInsurer(form.getCurrentInsurer())
                                .date(form.getDate().toString())
                                .formStatus(form.getFormStatus().toString())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
