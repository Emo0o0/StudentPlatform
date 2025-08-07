package com.example.core.service.scholarship;

import com.example.api.inputoutput.scholarship.meritincome.*;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.scholarship.ScholarshipApplyForm;
import com.example.persistence.entity.scholarship.ScholarshipType;
import com.example.persistence.entity.scholarship.banking.BankingInfo;
import com.example.persistence.entity.scholarship.meritincome.*;
import com.example.persistence.repository.banking.BankingInfoRepository;
import com.example.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import com.example.persistence.repository.scholarship.meritincome.MeritWithIncomeScholarshipInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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
    StudentContext studentContext;

    @Override
    public MeritIncomeScholarshipApplyResponse process(MeritIncomeScholarshipApplyRequest request) {

        MeritWithIncomeScholarshipInfo meritWithIncomeScholarshipInfo = MeritWithIncomeScholarshipInfo.builder()
                .familyStatus(FamilyStatus.valueOf(request.getFamilyStatus()))
                .familyIncomeInfo(mapFamilyIncome(request))
                .build();
        meritWithIncomeScholarshipInfoRepository.persist(meritWithIncomeScholarshipInfo);

        BankingInfo bankingInfo = BankingInfo.builder()
                .bankName(request.getBankingInfo().getBankName())
                .bankAccount(request.getBankingInfo().getBankAccount())
                .bankDocuments(null)
                .build();
        bankingInfoRepository.persist(bankingInfo);

        ScholarshipApplyForm scholarshipApplyForm = ScholarshipApplyForm.builder()
                .meritWithIncomeInfo(meritWithIncomeScholarshipInfo)
                .scholarshipType(ScholarshipType.SPECIAL_ACHIEVEMENTS)
                .bankingInfo(bankingInfo)
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

    private BigDecimal calculateTotalIncome(MeritIncomeScholarshipApplyRequest income){
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
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
