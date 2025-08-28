package com.example.core.service.scholarship;

import com.example.api.inputoutput.scholarship.foreign.ForeignScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.foreign.ForeignScholarshipApplyResponse;
import com.example.api.inputoutput.scholarship.foreign.ForeignScholarshipOperation;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.PersonalAcademicInfo;
import com.example.persistence.entity.enums.CourseYear;
import com.example.persistence.entity.enums.DegreeLevel;
import com.example.persistence.entity.enums.Faculty;
import com.example.persistence.entity.enums.Semester;
import com.example.persistence.entity.scholarship.ScholarshipApplyForm;
import com.example.persistence.entity.scholarship.ScholarshipType;
import com.example.persistence.entity.scholarship.banking.BankingInfo;
import com.example.persistence.entity.scholarship.foreign.ForeignStudentScholarshipInfo;
import com.example.persistence.repository.PersonalAcademicInfoRepository;
import com.example.persistence.repository.banking.BankingInfoRepository;
import com.example.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import com.example.persistence.repository.scholarship.foreign.ForeignStudentScholarshipInfoRepository;
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
                .student(studentContext.getCurrentStudent())
                .build();
        scholarshipApplyFormRepository.persist(scholarshipApplyForm);

        return ForeignScholarshipApplyResponse.builder()
                .success(true)
                .build();
    }
}
