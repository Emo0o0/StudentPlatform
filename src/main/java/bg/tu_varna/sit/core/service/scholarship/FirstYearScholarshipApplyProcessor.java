package bg.tu_varna.sit.core.service.scholarship;

import bg.tu_varna.sit.api.inputoutput.scholarship.firstyear.FirstYearScholarshipApplyOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.firstyear.FirstYearScholarshipApplyRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.firstyear.FirstYearScholarshipApplyResponse;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.enums.*;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipApplyForm;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipType;
import bg.tu_varna.sit.persistence.entity.scholarship.banking.BankingInfo;
import bg.tu_varna.sit.persistence.entity.scholarship.firstyear.FirstYearScholarshipInfo;
import bg.tu_varna.sit.persistence.entity.scholarship.firstyear.ProfessionalDirection;
import bg.tu_varna.sit.persistence.repository.PersonalAcademicInfoRepository;
import bg.tu_varna.sit.persistence.repository.banking.BankingInfoRepository;
import bg.tu_varna.sit.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import bg.tu_varna.sit.persistence.repository.scholarship.firstyear.FirstYearScholarshipInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FirstYearScholarshipApplyProcessor implements FirstYearScholarshipApplyOperation {
    @Inject
    FirstYearScholarshipInfoRepository firstYearScholarshipInfoRepository;
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
    public FirstYearScholarshipApplyResponse process(FirstYearScholarshipApplyRequest request) {

        FirstYearScholarshipInfo firstYearScholarshipInfo = FirstYearScholarshipInfo.builder()
                .professionalDirection(ProfessionalDirection.valueOf(request.getProfessionalDirection()))
                .bulgarianLanguageGrade(request.getBulgarianLanguageGrade())
                .secondExamSubject(request.getSecondExamSubject())
                .secondExamGrade(request.getSecondExamGrade())
                .firstYearDocuments(null)
                .build();
        firstYearScholarshipInfoRepository.persist(firstYearScholarshipInfo);

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

        BankingInfo bankingInfo = BankingInfo.builder()
                .bankName(request.getBankingInfo().getBankName())
                .bankAccount(request.getBankingInfo().getBankAccount())
                .bankDocuments(null)
                .build();
        bankingInfoRepository.persist(bankingInfo);

        ScholarshipApplyForm scholarshipApplyForm = ScholarshipApplyForm.builder()
                .personalAcademicInfo(personalAcademicInfo)
                .firstYearScholarshipInfo(firstYearScholarshipInfo)
                .scholarshipType(ScholarshipType.FIRST_YEAR)
                .bankingInfo(bankingInfo)
                .formStatus(FormStatus.SENT)
                .student(studentContext.getCurrentStudent())
                .build();
        scholarshipApplyFormRepository.persist(scholarshipApplyForm);

        return FirstYearScholarshipApplyResponse.builder()
                .success(true)
                .build();
    }
}
