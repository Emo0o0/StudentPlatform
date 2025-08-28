package bg.tu_varna.sit.core.service.scholarship;

import bg.tu_varna.sit.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyResponse;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipApplyForm;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipType;
import bg.tu_varna.sit.persistence.entity.scholarship.achievement.SpecialAchievementScholarshipInfo;
import bg.tu_varna.sit.persistence.entity.scholarship.banking.BankingInfo;
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

        ScholarshipApplyForm scholarshipApplyForm = ScholarshipApplyForm.builder()
                .specialAchievementScholarshipInfo(specialAchievementScholarshipInfo)
                .scholarshipType(ScholarshipType.SPECIAL_ACHIEVEMENTS)
                .bankingInfo(bankingInfo)
                .student(studentContext.getCurrentStudent())
                .build();
        scholarshipApplyFormRepository.persist(scholarshipApplyForm);

        return AchievementScholarshipApplyResponse.builder()
                .success(true)
                .build();
    }
}
