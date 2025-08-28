package com.example.core.service.scholarship;

import com.example.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyOperation;
import com.example.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyResponse;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.scholarship.ScholarshipApplyForm;
import com.example.persistence.entity.scholarship.ScholarshipType;
import com.example.persistence.entity.scholarship.achievement.SpecialAchievementScholarshipInfo;
import com.example.persistence.entity.scholarship.banking.BankingInfo;
import com.example.persistence.repository.banking.BankingInfoRepository;
import com.example.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import com.example.persistence.repository.scholarship.achievement.SpecialAchievementScholarshipInfoRepository;
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
