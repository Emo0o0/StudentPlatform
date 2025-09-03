package bg.tu_varna.sit.core.service.scholarship;

import bg.tu_varna.sit.api.inputoutput.scholarship.meritincome.*;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.enums.*;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipApplyForm;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipType;
import bg.tu_varna.sit.persistence.entity.scholarship.banking.BankingInfo;
import bg.tu_varna.sit.persistence.entity.scholarship.meritincome.*;
import bg.tu_varna.sit.persistence.repository.PersonalAcademicInfoRepository;
import bg.tu_varna.sit.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import bg.tu_varna.sit.persistence.repository.scholarship.meritincome.MeritWithIncomeScholarshipInfoRepository;
import bg.tu_varna.sit.persistence.repository.banking.BankingInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class MeritIncomeScholarshipApplyProcessor implements MeritIncomeScholarshipApplyOperation {
    @Inject
    MeritWithIncomeScholarshipInfoRepository meritWithIncomeScholarshipInfoRepository;
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
    public MeritIncomeScholarshipApplyResponse process(MeritIncomeScholarshipApplyRequest request) {

        FamilyStatus familyStatus = null;
        if (request.getFamilyStatus() != null && request.getFamilyStatus() != "") {
            familyStatus = FamilyStatus.valueOf(request.getFamilyStatus());
        }

        MeritWithIncomeScholarshipInfo meritWithIncomeScholarshipInfo = MeritWithIncomeScholarshipInfo.builder()
                .familyStatus(familyStatus)
                .familyIncomeInfo(mapFamilyIncome(request))
                .build();
        meritWithIncomeScholarshipInfoRepository.persist(meritWithIncomeScholarshipInfo);

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
                .previousGPA(request.getPreviousGPA())
                .meritWithIncomeInfo(meritWithIncomeScholarshipInfo)
                .scholarshipType(ScholarshipType.MERIT_WITH_INCOME)
                .bankingInfo(bankingInfo)
                .formStatus(FormStatus.SENT)
                .student(studentContext.getCurrentStudent())
                .build();
        scholarshipApplyFormRepository.persist(scholarshipApplyForm);

        return MeritIncomeScholarshipApplyResponse.builder()
                .success(true)
                .build();
    }


    private FamilyIncomeInfo mapFamilyIncome(MeritIncomeScholarshipApplyRequest info) {
        return FamilyIncomeInfo.builder()
                .spouseName(info.getSpouseName())
                .spouseEmploymentStatus(info.getSpouseEmploymentStatus())
                .children(mapChildren(info.getChildren()))
                .fatherName(info.getFatherName())
                .fatherStatus(info.getFatherStatus())
                .motherName(info.getMotherName())
                .motherStatus(info.getMotherStatus())
                .siblings(mapSiblings(info.getSiblings()))
                .salaries(info.getSalaries())
                .pensions(info.getPensions())
                .unemploymentBenefits(info.getUnemploymentBenefits())
                .socialAid(info.getSocialAid())
                .familyAllowances(info.getFamilyAllowances())
                .childCareAllowances(info.getChildCareAllowances())
                .personalScholarships(info.getPersonalScholarships())
                .rent(info.getRent())
                .honorariums(info.getHonorariums())
                .alimony(info.getAlimony())
                .businessIncome(info.getBusinessIncome())
                .otherIncome(info.getOtherIncome())
                .totalIncome(calculateTotalIncome(info))
                .monthlyIncomePerMember(info.getMonthlyIncomePerMember())
                .incomeDocuments(null)
                .build();
    }

    private List<Child> mapChildren(List<ChildDTO> children) {
        return children.stream()
                .map((child) -> Child.builder()
                        .fullName(child.getFullName())
                        .birthDate(child.getBirthDate())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Sibling> mapSiblings(List<SiblingDTO> siblings) {
        return siblings.stream()
                .map((sibling) -> Sibling.builder()
                        .fullName(sibling.getFullName())
                        .educationStatus(sibling.getEducationStatus())
                        .build())
                .collect(Collectors.toList());
    }

    private BigDecimal calculateTotalIncome(MeritIncomeScholarshipApplyRequest income) {
        return Stream.of(income.getSalaries(),
                        income.getPensions(),
                        income.getUnemploymentBenefits(),
                        income.getSocialAid(),
                        income.getFamilyAllowances(),
                        income.getChildCareAllowances(),
                        income.getPersonalScholarships(),
                        income.getRent(),
                        income.getHonorariums(),
                        income.getAlimony(),
                        income.getBusinessIncome(),
                        income.getOtherIncome())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
