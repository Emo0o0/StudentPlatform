package bg.tu_varna.sit.core.service.scholarship;

import bg.tu_varna.sit.api.inputoutput.scholarship.social.SocialScholarshipApplyOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.social.SocialScholarshipApplyRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.social.SocialScholarshipApplyResponse;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipApplyForm;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipType;
import bg.tu_varna.sit.persistence.entity.scholarship.banking.BankingInfo;
import bg.tu_varna.sit.persistence.entity.scholarship.social.SocialScholarshipInfo;
import bg.tu_varna.sit.persistence.entity.scholarship.social.SocialScholarshipType;
import bg.tu_varna.sit.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import bg.tu_varna.sit.persistence.repository.scholarship.social.SocialScholarshipInfoRepository;
import bg.tu_varna.sit.persistence.repository.banking.BankingInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
    @Transactional
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
