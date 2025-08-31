package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.late.StudentGetInsuranceLateFormsListResponse;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.late.StudentGetInsuranceLateFormsOperation;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.late.StudentGetInsuranceLateFormsRequest;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.late.StudentGetInsuranceLateFormsResponse;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceLateForm;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceLateFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudentGetInsuranceLateFormsProcessor implements StudentGetInsuranceLateFormsOperation {

    @Inject
    HealthInsuranceLateFormRepository healthInsuranceLateFormRepository;

    @Override
    public StudentGetInsuranceLateFormsListResponse process(StudentGetInsuranceLateFormsRequest request) {
        Long studentId = request.getStudentId()==null ? null : Long.valueOf(request.getStudentId());

        List<HealthInsuranceLateForm> insuranceLateForms = healthInsuranceLateFormRepository.getStudentForms(studentId, request.getSpecialty());

        return StudentGetInsuranceLateFormsListResponse.builder()
                .forms(insuranceLateForms.stream()
                        .map(form -> StudentGetInsuranceLateFormsResponse.builder()
                                .formId(form.getFormId().toString())
                                .studentFirstName(form.getPersonalAcademicInfo().getFirstName())
                                .studentLastName(form.getPersonalAcademicInfo().getLastName())
                                .studentFacultyNumber(form.getPersonalAcademicInfo().getFacultyNumber())
                                .year(form.getSchoolYear())
                                .date(form.getDate().toString())
                                .formStatus(form.getFormStatus().toString())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
