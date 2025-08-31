package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.terminate.StudentGetInsuranceTerminateFormsListResponse;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.terminate.StudentGetInsuranceTerminateFormsOperation;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.terminate.StudentGetInsuranceTerminateFormsRequest;
import bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.terminate.StudentGetInsuranceTerminateFormsResponse;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceTerminateForm;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceTerminateFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudentGetInsuranceTerminateFormsProcessor implements StudentGetInsuranceTerminateFormsOperation {

    @Inject
    HealthInsuranceTerminateFormRepository healthInsuranceTerminateFormRepository;
    @Override
    public StudentGetInsuranceTerminateFormsListResponse process(StudentGetInsuranceTerminateFormsRequest request) {
        Long studentId = request.getStudentId()==null ? null : Long.valueOf(request.getStudentId());

        List<HealthInsuranceTerminateForm> insuranceLateForms = healthInsuranceTerminateFormRepository.getStudentForms(studentId, request.getSpecialty());

        return StudentGetInsuranceTerminateFormsListResponse.builder()
                .forms(insuranceLateForms.stream()
                        .map(form -> StudentGetInsuranceTerminateFormsResponse.builder()
                                .formId(form.getFormId().toString())
                                .studentFirstName(form.getPersonalAcademicInfo().getFirstName())
                                .studentLastName(form.getPersonalAcademicInfo().getLastName())
                                .studentFacultyNumber(form.getPersonalAcademicInfo().getFacultyNumber())
                                .terminationReason(form.getTerminationReason())
                                .date(form.getDate().toString())
                                .formStatus(form.getFormStatus().toString())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
