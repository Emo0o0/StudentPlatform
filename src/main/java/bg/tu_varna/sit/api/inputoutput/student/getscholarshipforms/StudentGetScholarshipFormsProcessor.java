package bg.tu_varna.sit.api.inputoutput.student.getscholarshipforms;

import bg.tu_varna.sit.api.inputoutput.scholarship.BankingInfoDTO;
import bg.tu_varna.sit.api.inputoutput.scholarship.meritincome.ChildDTO;
import bg.tu_varna.sit.api.inputoutput.scholarship.meritincome.SiblingDTO;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipApplyForm;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipType;
import bg.tu_varna.sit.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudentGetScholarshipFormsProcessor implements StudentGetScholarshipFormsOperation {

    @Inject
    ScholarshipApplyFormRepository scholarshipApplyFormRepository;

    @Override
    public StudentGetScholarshipFormsListResponse process(StudentGetScholarshipFormsRequest request) {

        Long studentId = request.getStudentId() == null ? null : Long.valueOf(request.getStudentId());
        ScholarshipType type = request.getScholarshipType() == null ? null : ScholarshipType.valueOf(request.getScholarshipType());

        List<ScholarshipApplyForm> scholarshipApplyForms = scholarshipApplyFormRepository.getStudentForms(studentId, request.getSpecialty(), type);

        return StudentGetScholarshipFormsListResponse.builder()
                .forms(
                        scholarshipApplyForms.stream()
                                .map(form -> switch (form.getScholarshipType().toString()) {
                                    case "MERIT_SUCCESS" -> mapMeritSuccess(form);
                                    case "MERIT_WITH_INCOME" -> mapMeritWithIncome(form);
                                    case "SOCIAL_PREFERENTIAL" -> mapSocial(form);
                                    case "FOREIGN_STUDENT" -> mapForeign(form);
                                    case "FIRST_YEAR" -> mapFirstYear(form);
                                    case "SPECIAL_ACHIEVEMENTS" -> mapSpecialAchievements(form);
                                    default -> null;
                                }).collect(Collectors.toList()))
                .build();

    }

    private StudentGetScholarshipFormsResponse mapSpecialAchievements(ScholarshipApplyForm form) {
        return StudentGetScholarshipFormsResponse.builder()
                .formId(form.getFormId().toString())
                .studentFirstName(form.getPersonalAcademicInfo().getFirstName())
                .studentLastName(form.getPersonalAcademicInfo().getLastName())
                .studentFacultyNumber(form.getPersonalAcademicInfo().getFacultyNumber())
                .previousGPA(form.getPreviousGPA().toString())
                .scholarshipType(form.getScholarshipType().toString())
                .meritWithIncomeScholarship(null)
                .socialScholarship(null)
                .firstYearScholarship(null)
                .specialAchievementScholarship(SpecialAchievementScholarshipDTO.builder()
                        .achievementTopic(form.getSpecialAchievementScholarshipInfo().getAchievementTopic())
                        .build())
                .bankingInfo(BankingInfoDTO.builder()
                        .bankName(form.getBankingInfo().getBankName())
                        .bankAccount(form.getBankingInfo().getBankAccount())
                        .build())
                .formStatus(form.getFormStatus().toString())
                .build();
    }

    private StudentGetScholarshipFormsResponse mapFirstYear(ScholarshipApplyForm form) {
        return StudentGetScholarshipFormsResponse.builder()
                .formId(form.getFormId().toString())
                .studentFirstName(form.getPersonalAcademicInfo().getFirstName())
                .studentLastName(form.getPersonalAcademicInfo().getLastName())
                .studentFacultyNumber(form.getPersonalAcademicInfo().getFacultyNumber())
                .previousGPA(form.getPreviousGPA().toString())
                .scholarshipType(form.getScholarshipType().toString())
                .meritWithIncomeScholarship(null)
                .socialScholarship(null)
                .firstYearScholarship(FirstYearScholarshipDTO.builder()
                        .bulgarianLanguageGrade(form.getFirstYearScholarshipInfo().getBulgarianLanguageGrade().toString())
                        .secondExamSubject(form.getFirstYearScholarshipInfo().getSecondExamSubject())
                        .secondExamGrade(form.getFirstYearScholarshipInfo().getSecondExamGrade().toString())
                        .build())
                .specialAchievementScholarship(null)
                .bankingInfo(BankingInfoDTO.builder()
                        .bankName(form.getBankingInfo().getBankName())
                        .bankAccount(form.getBankingInfo().getBankAccount())
                        .build())
                .formStatus(form.getFormStatus().toString())
                .build();
    }

    private StudentGetScholarshipFormsResponse mapForeign(ScholarshipApplyForm form) {
        return StudentGetScholarshipFormsResponse.builder()
                .formId(form.getFormId().toString())
                .studentFirstName(form.getPersonalAcademicInfo().getFirstName())
                .studentLastName(form.getPersonalAcademicInfo().getLastName())
                .studentFacultyNumber(form.getPersonalAcademicInfo().getFacultyNumber())
                .previousGPA(form.getPreviousGPA().toString())
                .scholarshipType(form.getScholarshipType().toString())
                .meritWithIncomeScholarship(null)
                .socialScholarship(null)
                .firstYearScholarship(null)
                .specialAchievementScholarship(null)
                .bankingInfo(BankingInfoDTO.builder()
                        .bankName(form.getBankingInfo().getBankName())
                        .bankAccount(form.getBankingInfo().getBankAccount())
                        .build())
                .formStatus(form.getFormStatus().toString())
                .build();
    }

    private StudentGetScholarshipFormsResponse mapSocial(ScholarshipApplyForm form) {
        return StudentGetScholarshipFormsResponse.builder()
                .formId(form.getFormId().toString())
                .studentFirstName(form.getPersonalAcademicInfo().getFirstName())
                .studentLastName(form.getPersonalAcademicInfo().getLastName())
                .studentFacultyNumber(form.getPersonalAcademicInfo().getFacultyNumber())
                .previousGPA(form.getPreviousGPA().toString())
                .scholarshipType(form.getScholarshipType().toString())
                .meritWithIncomeScholarship(null)
                .socialScholarship(SocialScholarshipDTO.builder()
                        .socialScholarshipType(form.getSocialScholarshipInfo().getSocialType().toString())
                        .hasMarriage(form.getSocialScholarshipInfo().getHasMarriage())
                        .build())
                .firstYearScholarship(null)
                .specialAchievementScholarship(null)
                .bankingInfo(BankingInfoDTO.builder()
                        .bankName(form.getBankingInfo().getBankName())
                        .bankAccount(form.getBankingInfo().getBankAccount())
                        .build())
                .formStatus(form.getFormStatus().toString())
                .build();
    }

    private StudentGetScholarshipFormsResponse mapMeritSuccess(ScholarshipApplyForm form) {
        return StudentGetScholarshipFormsResponse.builder()
                .formId(form.getFormId().toString())
                .studentFirstName(form.getPersonalAcademicInfo().getFirstName())
                .studentLastName(form.getPersonalAcademicInfo().getLastName())
                .studentFacultyNumber(form.getPersonalAcademicInfo().getFacultyNumber())
                .previousGPA(form.getPreviousGPA().toString())
                .scholarshipType(form.getScholarshipType().toString())
                .meritWithIncomeScholarship(null)
                .socialScholarship(null)
                .firstYearScholarship(null)
                .specialAchievementScholarship(null)
                .bankingInfo(BankingInfoDTO.builder()
                        .bankName(form.getBankingInfo().getBankName())
                        .bankAccount(form.getBankingInfo().getBankAccount())
                        .build())
                .formStatus(form.getFormStatus().toString())
                .build();
    }

    private StudentGetScholarshipFormsResponse mapMeritWithIncome(ScholarshipApplyForm form) {
        return StudentGetScholarshipFormsResponse.builder()
                .formId(form.getFormId().toString())
                .studentFirstName(form.getPersonalAcademicInfo().getFirstName())
                .studentLastName(form.getPersonalAcademicInfo().getLastName())
                .studentFacultyNumber(form.getPersonalAcademicInfo().getFacultyNumber())
                .previousGPA(form.getPreviousGPA().toString())
                .scholarshipType(form.getScholarshipType().toString())
                .meritWithIncomeScholarship(MeritWithIncomeScholarshipDTO.builder()
                        .familyStatus(form.getMeritWithIncomeInfo().getFamilyStatus().toString())
                        .spouseName(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getSpouseName())
                        .spouseEmploymentStatus(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getSpouseEmploymentStatus())
                        .children(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getChildren().stream()
                                .map(child -> ChildDTO.builder()
                                        .fullName(child.getFullName())
                                        .birthDate(child.getBirthDate())
                                        .build())
                                .collect(Collectors.toList()))
                        .fatherName(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getFatherName())
                        .fatherStatus(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getFatherStatus())
                        .motherName(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getMotherName())
                        .motherStatus(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getMotherStatus())
                        .siblings(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getSiblings().stream()
                                .map(sibling -> SiblingDTO.builder()
                                        .fullName(sibling.getFullName())
                                        .educationStatus(sibling.getEducationStatus())
                                        .build())
                                .collect(Collectors.toList()))
                        .salaries(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getSalaries().toString())
                        .pensions(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getPensions().toString())
                        .unemploymentBenefits(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getUnemploymentBenefits().toString())
                        .socialAid(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getSocialAid().toString())
                        .familyAllowances(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getFamilyAllowances().toString())
                        .childCareAllowances(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getChildCareAllowances().toString())
                        .personalScholarships(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getPersonalScholarships().toString())
                        .rent(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getRent().toString())
                        .honorariums(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getHonorariums().toString())
                        .alimony(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getAlimony().toString())
                        .businessIncome(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getBusinessIncome().toString())
                        .otherIncome(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getOtherIncome().toString())
                        .totalIncome(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getTotalIncome().toString())
                        .monthlyIncomePerMember(form.getMeritWithIncomeInfo().getFamilyIncomeInfo().getMonthlyIncomePerMember().toString())
                        .build())
                .socialScholarship(null)
                .firstYearScholarship(null)
                .specialAchievementScholarship(null)
                .bankingInfo(BankingInfoDTO.builder()
                        .bankName(form.getBankingInfo().getBankName())
                        .bankAccount(form.getBankingInfo().getBankAccount())
                        .build())
                .formStatus(form.getFormStatus().toString())
                .build();
    }
}
