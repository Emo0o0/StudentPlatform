package com.example.core.service.scholarship;

import com.example.api.inputoutput.scholarship.merit.MeritScholarshipApplyOperation;
import com.example.api.inputoutput.scholarship.merit.MeritScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.merit.MeritScholarshipApplyResponse;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.scholarship.ScholarshipApplyForm;
import com.example.persistence.entity.scholarship.ScholarshipType;
import com.example.persistence.entity.scholarship.banking.BankingInfo;
import com.example.persistence.entity.scholarship.merit.MeritScholarshipInfo;
import com.example.persistence.repository.banking.BankingInfoRepository;
import com.example.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import com.example.persistence.repository.scholarship.merit.MeritScholarshipInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MeritScholarshipApplyProcessor implements MeritScholarshipApplyOperation {

    @Inject
    MeritScholarshipInfoRepository meritScholarshipInfoRepository;
    @Inject
    ScholarshipApplyFormRepository scholarshipApplyFormRepository;
    @Inject
    BankingInfoRepository bankingInfoRepository;
    @Inject
    StudentContext studentContext;

    @Override
    public MeritScholarshipApplyResponse process(MeritScholarshipApplyRequest request) {

        MeritScholarshipInfo meritScholarshipInfo = MeritScholarshipInfo.builder()
                .build();
        meritScholarshipInfoRepository.persist(meritScholarshipInfo);

        BankingInfo bankingInfo = BankingInfo.builder()
                .bankName(request.getBankingInfo().getBankName())
                .bankAccount(request.getBankingInfo().getBankAccount())
                .bankDocuments(null)
                .build();
        bankingInfoRepository.persist(bankingInfo);

        ScholarshipApplyForm scholarshipApplyForm = ScholarshipApplyForm.builder()
                .meritScholarshipInfo(meritScholarshipInfo)
                .scholarshipType(ScholarshipType.MERIT_SUCCESS)
                .bankingInfo(bankingInfo)
                .student(studentContext.getCurrentStudent())
                .build();
        scholarshipApplyFormRepository.persist(scholarshipApplyForm);

        return MeritScholarshipApplyResponse.builder()
                .success(true)
                .build();
    }
}
