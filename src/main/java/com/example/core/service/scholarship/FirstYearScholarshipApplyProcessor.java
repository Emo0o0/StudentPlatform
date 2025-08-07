package com.example.core.service.scholarship;

import com.example.api.inputoutput.scholarship.firstyear.FirstYearScholarshipApplyOperation;
import com.example.api.inputoutput.scholarship.firstyear.FirstYearScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.firstyear.FirstYearScholarshipApplyResponse;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.scholarship.ScholarshipApplyForm;
import com.example.persistence.entity.scholarship.ScholarshipType;
import com.example.persistence.entity.scholarship.banking.BankingInfo;
import com.example.persistence.entity.scholarship.firstyear.FirstYearScholarshipInfo;
import com.example.persistence.entity.scholarship.firstyear.ProfessionalDirection;
import com.example.persistence.repository.banking.BankingInfoRepository;
import com.example.persistence.repository.scholarship.ScholarshipApplyFormRepository;
import com.example.persistence.repository.scholarship.firstyear.FirstYearScholarshipInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FirstYearScholarshipApplyProcessor implements FirstYearScholarshipApplyOperation {
    @Inject
    FirstYearScholarshipInfoRepository firstYearScholarshipInfoRepository;
    @Inject
    ScholarshipApplyFormRepository scholarshipApplyFormRepository;
    @Inject
    BankingInfoRepository bankingInfoRepository;
    @Inject
    StudentContext studentContext;

    @Override
    public FirstYearScholarshipApplyResponse process(FirstYearScholarshipApplyRequest request) {

        FirstYearScholarshipInfo firstYearScholarshipInfo = FirstYearScholarshipInfo.builder()
                .professionalDirection(ProfessionalDirection.valueOf(request.getProfessionalDirection()))
                .bulgarianLanguageGrade(request.getBulgarianLanguageGrade())
                .secondExamSubject(request.getSecondExamSubject())
                .secondExamGrade(request.getSecondExamGrade())
                .firstYearDocuments(null)
                .build();
        firstYearScholarshipInfoRepository.persist(firstYearScholarshipInfo);

        BankingInfo bankingInfo = BankingInfo.builder()
                .bankName(request.getBankingInfo().getBankName())
                .bankAccount(request.getBankingInfo().getBankAccount())
                .bankDocuments(null)
                .build();
        bankingInfoRepository.persist(bankingInfo);

        ScholarshipApplyForm scholarshipApplyForm = ScholarshipApplyForm.builder()
                .firstYearScholarshipInfo(firstYearScholarshipInfo)
                .scholarshipType(ScholarshipType.FIRST_YEAR)
                .bankingInfo(bankingInfo)
                .student(studentContext.getCurrentStudent())
                .build();
        scholarshipApplyFormRepository.persist(scholarshipApplyForm);

        return FirstYearScholarshipApplyResponse.builder()
                .success(true)
                .build();
    }
}
