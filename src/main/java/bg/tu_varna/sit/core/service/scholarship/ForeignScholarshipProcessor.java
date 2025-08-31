package bg.tu_varna.sit.core.service.scholarship;

import bg.tu_varna.sit.api.inputoutput.scholarship.foreign.ForeignScholarshipApplyRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.foreign.ForeignScholarshipApplyResponse;
import bg.tu_varna.sit.api.inputoutput.scholarship.foreign.ForeignScholarshipOperation;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.enums.*;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipApplyForm;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipType;
import bg.tu_varna.sit.persistence.entity.scholarship.banking.BankingInfo;
import bg.tu_varna.sit.persistence.entity.scholarship.foreign.ForeignStudentScholarshipInfo;
import bg.tu_varna.sit.persistence.repository.PersonalAcademicInfoRepository;
import bg.tu_varna.sit.persistence.repository.banking.BankingInfoRepository;
import bg.tu_varna.sit.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import bg.tu_varna.sit.persistence.repository.scholarship.foreign.ForeignStudentScholarshipInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ForeignScholarshipProcessor implements ForeignScholarshipOperation {

    @Inject
    ForeignStudentScholarshipInfoRepository foreignStudentScholarshipInfoRepository;
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
    public ForeignScholarshipApplyResponse process(ForeignScholarshipApplyRequest request) {

        ForeignStudentScholarshipInfo foreignStudentScholarshipInfo = ForeignStudentScholarshipInfo.builder()
                .foreignStudentDocuments(null)
                .build();
        foreignStudentScholarshipInfoRepository.persist(foreignStudentScholarshipInfo);

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
                .foreignStudentScholarshipInfo(foreignStudentScholarshipInfo)
                .scholarshipType(ScholarshipType.FOREIGN_STUDENT)
                .bankingInfo(bankingInfo)
                .formStatus(FormStatus.SENT)
                .student(studentContext.getCurrentStudent())
                .build();
        scholarshipApplyFormRepository.persist(scholarshipApplyForm);

        return ForeignScholarshipApplyResponse.builder()
                .success(true)
                .build();
    }
}
