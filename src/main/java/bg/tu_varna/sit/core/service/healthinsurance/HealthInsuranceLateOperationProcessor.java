package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateResponse;
import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.enums.CourseYear;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
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

        PersonalAcademicInfo personalAcademicInfo = PersonalAcademicInfo.builder()
                .firstName(request.getPersonalAcademicInfo().getFirstName())
                .secondName(request.getPersonalAcademicInfo().getSecondName())
                .lastName(request.getPersonalAcademicInfo().getLastName())
                .egn(request.getPersonalAcademicInfo().getEgn())
                .phoneNumber(request.getPersonalAcademicInfo().getPhoneNumber())
                .courseYear(CourseYear.valueOf(request.getPersonalAcademicInfo().getCourseYear()))
                .specialty(request.getPersonalAcademicInfo().getSpecialty())
                .facultyNumber(request.getPersonalAcademicInfo().getFacultyNumber())
                .faculty(Faculty.valueOf(request.getPersonalAcademicInfo().getFaculty()))
                .placeOfResidence(request.getPersonalAcademicInfo().getPlaceOfResidence())
                .streetName(request.getPersonalAcademicInfo().getStreetName())
                .streetNumber(request.getPersonalAcademicInfo().getStreetNumber())
                .entrance(request.getPersonalAcademicInfo().getEntrance())
                .floor(request.getPersonalAcademicInfo().getFloor())
                .flatNumber(request.getPersonalAcademicInfo().getFlatNumber())
                .build();

        HealthInsuranceLateForm healthInsuranceLateForm = HealthInsuranceLateForm.builder()
                .personalAcademicInfo(personalAcademicInfo)
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
