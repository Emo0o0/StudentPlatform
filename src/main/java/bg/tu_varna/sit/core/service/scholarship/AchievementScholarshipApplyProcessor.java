package bg.tu_varna.sit.core.service.scholarship;

import bg.tu_varna.sit.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyResponse;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.enums.*;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipApplyForm;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipType;
import bg.tu_varna.sit.persistence.entity.scholarship.achievement.SpecialAchievementScholarshipInfo;
import bg.tu_varna.sit.persistence.entity.scholarship.banking.BankingInfo;
import bg.tu_varna.sit.persistence.repository.PersonalAcademicInfoRepository;
import bg.tu_varna.sit.persistence.repository.banking.BankingInfoRepository;
import bg.tu_varna.sit.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import bg.tu_varna.sit.persistence.repository.scholarship.achievement.SpecialAchievementScholarshipInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AchievementScholarshipApplyProcessor implements AchievementScholarshipApplyOperation {

    @Inject
    SpecialAchievementScholarshipInfoRepository specialAchievementScholarshipInfoRepository;
    @Inject
    ScholarshipApplyFormRepository scholarshipApplyFormRepository;
    @Inject
    BankingInfoRepository bankingInfoRepository;
    @Inject
    PersonalAcademicInfoRepository personalAcademicInfoRepository;
    @Inject
    StudentContext studentContext;

    @Override
    @Transactional
    public AchievementScholarshipApplyResponse process(AchievementScholarshipApplyRequest request) {

        SpecialAchievementScholarshipInfo specialAchievementScholarshipInfo = SpecialAchievementScholarshipInfo.builder()
                .achievementTopic(request.getAchievementTopic())
                .achievementDocuments(null)
                .build();
        specialAchievementScholarshipInfoRepository.persist(specialAchievementScholarshipInfo);

        BankingInfo bankingInfo = BankingInfo.builder()
                .bankName(request.getBankingInfo().getBankName())
                .bankAccount(request.getBankingInfo().getBankAccount())
                .bankDocuments(null)
                .build();
        bankingInfoRepository.persist(bankingInfo);

        PersonalAcademicInfo personalAcademicInfo = PersonalAcademicInfo.builder()
                .email(request.getPersonalAcademicInfo().getEmail())
                .firstName(request.getPersonalAcademicInfo().getFirstName())
                .secondName(request.getPersonalAcademicInfo().getSecondName())
                .lastName(request.getPersonalAcademicInfo().getLastName())
                .egn(request.getPersonalAcademicInfo().getEgn())
                .address(request.getPersonalAcademicInfo().getAddress())
                .phoneNumber(request.getPersonalAcademicInfo().getPhoneNumber())
                .placeOfResidence(request.getPersonalAcademicInfo().getPlaceOfResidence())
                .streetName(request.getPersonalAcademicInfo().getStreetName())
                .streetNumber(request.getPersonalAcademicInfo().getStreetNumber())
                .entrance(request.getPersonalAcademicInfo().getEntrance())
                .floor(request.getPersonalAcademicInfo().getFloor())
                .flatNumber(request.getPersonalAcademicInfo().getFlatNumber())
                .facultyNumber(request.getPersonalAcademicInfo().getFacultyNumber())
                .courseYear(CourseYear.valueOf(request.getPersonalAcademicInfo().getCourseYear()))
                .semester(Semester.valueOf(request.getPersonalAcademicInfo().getSemester()))
                .degreeLevel(DegreeLevel.valueOf(request.getPersonalAcademicInfo().getDegreeLevel()))
                .faculty(Faculty.valueOf(request.getPersonalAcademicInfo().getFaculty()))
                .specialty(request.getPersonalAcademicInfo().getSpecialty())
                .studentGroup(request.getPersonalAcademicInfo().getStudentGroup())
                .subGroup(request.getPersonalAcademicInfo().getSubGroup())
                .build();
        personalAcademicInfoRepository.persist(personalAcademicInfo);

        ScholarshipApplyForm scholarshipApplyForm = ScholarshipApplyForm.builder()
                .specialAchievementScholarshipInfo(specialAchievementScholarshipInfo)
                .personalAcademicInfo(personalAcademicInfo)
                .scholarshipType(ScholarshipType.SPECIAL_ACHIEVEMENTS)
                .bankingInfo(bankingInfo)
                .formStatus(FormStatus.SENT)
                .student(studentContext.getCurrentStudent())
                .build();
        scholarshipApplyFormRepository.persist(scholarshipApplyForm);

        return AchievementScholarshipApplyResponse.builder()
                .success(true)
                .build();
    }
}
