package com.example.core.service.scholarship;

import com.example.api.inputoutput.scholarship.social.SocialScholarshipApplyOperation;
import com.example.api.inputoutput.scholarship.social.SocialScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.social.SocialScholarshipApplyResponse;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.scholarship.ScholarshipApplyForm;
import com.example.persistence.entity.scholarship.ScholarshipType;
import com.example.persistence.entity.scholarship.banking.BankingInfo;
import com.example.persistence.entity.scholarship.social.SocialScholarshipInfo;
import com.example.persistence.entity.scholarship.social.SocialScholarshipType;
import com.example.persistence.repository.banking.BankingInfoRepository;
import com.example.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import com.example.persistence.repository.scholarship.social.SocialScholarshipInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SocialScholarshipApplyProcessor implements SocialScholarshipApplyOperation {

    @Inject
    SocialScholarshipInfoRepository socialScholarshipInfoRepository;
    @Inject
    ScholarshipApplyFormRepository scholarshipApplyFormRepository;
    @Inject
    BankingInfoRepository bankingInfoRepository;
    @Inject
    StudentContext studentContext;

    @Override
    public SocialScholarshipApplyResponse process(SocialScholarshipApplyRequest request) {

        SocialScholarshipInfo socialScholarshipInfo = SocialScholarshipInfo.builder()
                .socialType(SocialScholarshipType.valueOf(request.getSocialType()))
                .socialDocuments(null)
                .hasMarriage(request.getHasMarriage())
                .build();
        socialScholarshipInfoRepository.persist(socialScholarshipInfo);

        BankingInfo bankingInfo = BankingInfo.builder()
                .bankName(request.getBankingInfo().getBankName())
                .bankAccount(request.getBankingInfo().getBankAccount())
                .bankDocuments(null)
                .build();
        bankingInfoRepository.persist(bankingInfo);

        ScholarshipApplyForm scholarshipApplyForm = ScholarshipApplyForm.builder()
                .socialScholarshipInfo(socialScholarshipInfo)
                .scholarshipType(ScholarshipType.SPECIAL_ACHIEVEMENTS)
                .bankingInfo(bankingInfo)
                .student(studentContext.getCurrentStudent())
                .build();
        scholarshipApplyFormRepository.persist(scholarshipApplyForm);

        return SocialScholarshipApplyResponse.builder()
                .success(true)
                .build();
    }
}
