package com.example.core.service.scholarship;

import com.example.api.inputoutput.scholarship.foreign.ForeignScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.foreign.ForeignScholarshipApplyResponse;
import com.example.api.inputoutput.scholarship.foreign.ForeignScholarshipOperation;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.scholarship.ScholarshipApplyForm;
import com.example.persistence.entity.scholarship.ScholarshipType;
import com.example.persistence.entity.scholarship.banking.BankingInfo;
import com.example.persistence.entity.scholarship.foreign.ForeignStudentScholarshipInfo;
import com.example.persistence.repository.banking.BankingInfoRepository;
import com.example.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import com.example.persistence.repository.scholarship.foreign.ForeignStudentScholarshipInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ForeignScholarshipProcessor implements ForeignScholarshipOperation {

    @Inject
    ForeignStudentScholarshipInfoRepository foreignStudentScholarshipInfoRepository;
    @Inject
    ScholarshipApplyFormRepository scholarshipApplyFormRepository;
    @Inject
    BankingInfoRepository bankingInfoRepository;
    @Inject
    StudentContext studentContext;

    @Override
    public ForeignScholarshipApplyResponse process(ForeignScholarshipApplyRequest request) {

        ForeignStudentScholarshipInfo foreignStudentScholarshipInfo = ForeignStudentScholarshipInfo.builder()
                .foreignStudentDocuments(null)
                .build();
        foreignStudentScholarshipInfoRepository.persist(foreignStudentScholarshipInfo);

        BankingInfo bankingInfo = BankingInfo.builder()
                .bankName(request.getBankingInfo().getBankName())
                .bankAccount(request.getBankingInfo().getBankAccount())
                .bankDocuments(null)
                .build();
        bankingInfoRepository.persist(bankingInfo);

        ScholarshipApplyForm scholarshipApplyForm = ScholarshipApplyForm.builder()
                .foreignStudentScholarshipInfo(foreignStudentScholarshipInfo)
                .scholarshipType(ScholarshipType.FOREIGN_STUDENT)
                .bankingInfo(bankingInfo)
                .student(studentContext.getCurrentStudent())
                .build();
        scholarshipApplyFormRepository.persist(scholarshipApplyForm);

        return null;
    }
}
