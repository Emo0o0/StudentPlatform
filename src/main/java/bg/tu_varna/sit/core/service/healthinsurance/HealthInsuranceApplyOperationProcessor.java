package bg.tu_varna.sit.core.service.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyOperation;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyResponse;
import bg.tu_varna.sit.core.exception.InvalidEnumValueException;
import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.enums.CourseYear;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
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

        validateEnums(request);

        PersonalAcademicInfo personalAcademicInfo = PersonalAcademicInfo.builder()
                .firstName(request.getPersonalAcademicInfo().getFirstName())
                .secondName(request.getPersonalAcademicInfo().getSecondName())
                .lastName(request.getPersonalAcademicInfo().getLastName())
                .egn(request.getPersonalAcademicInfo().getEgn())
                .phoneNumber(request.getPersonalAcademicInfo().getPhoneNumber())
                .courseYear(CourseYear.valueOf(request.getPersonalAcademicInfo().getCourseYear()))
                .specialty(request.getPersonalAcademicInfo().getSpecialty())
                .faculty(Faculty.valueOf(request.getPersonalAcademicInfo().getFaculty()))
                .facultyNumber(request.getPersonalAcademicInfo().getFacultyNumber())
                .placeOfResidence(request.getPersonalAcademicInfo().getPlaceOfResidence())
                .streetName(request.getPersonalAcademicInfo().getStreetName())
                .streetNumber(request.getPersonalAcademicInfo().getStreetNumber())
                .entrance(request.getPersonalAcademicInfo().getEntrance())
                .floor(request.getPersonalAcademicInfo().getFloor())
                .flatNumber(request.getPersonalAcademicInfo().getFlatNumber())
                .build();

        HealthInsuranceApplyForm healthInsuranceApplyForm = HealthInsuranceApplyForm.builder()
                .personalAcademicInfo(personalAcademicInfo)
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

    private void validateEnums(HealthInsuranceApplyRequest request){
        try{
            CourseYear.valueOf(request.getPersonalAcademicInfo().getCourseYear());
            Faculty.valueOf(request.getPersonalAcademicInfo().getFaculty());
        } catch (IllegalArgumentException e){
            throw new InvalidEnumValueException("Invalid value: " + request.getPersonalAcademicInfo().getCourseYear());
        }
    }
}
