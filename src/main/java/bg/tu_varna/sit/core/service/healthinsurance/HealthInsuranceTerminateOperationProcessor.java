package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateResponse;
import bg.tu_varna.sit.core.exception.InvalidEnumValueException;
import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.enums.CourseYear;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
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

        validateEnums(request);

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

        HealthInsuranceTerminateForm healthInsuranceTerminateForm = HealthInsuranceTerminateForm.builder()
                .personalAcademicInfo(personalAcademicInfo)
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

    private void validateEnums(HealthInsuranceTerminateRequest request){
        try{
            CourseYear.valueOf(request.getPersonalAcademicInfo().getCourseYear());
            Faculty.valueOf(request.getPersonalAcademicInfo().getFaculty());
        } catch (IllegalArgumentException e){
            throw new InvalidEnumValueException("Invalid value: " + request.getPersonalAcademicInfo().getCourseYear());
        }
    }
}
