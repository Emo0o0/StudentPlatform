package bg.tu_varna.sit.api.inputoutput.scholarship.firstyear;

import bg.tu_varna.sit.api.contract.OperationRequest;
import bg.tu_varna.sit.api.PersonalAcademicInfoDTO;
import bg.tu_varna.sit.api.inputoutput.scholarship.BankingInfoDTO;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstYearScholarshipApplyRequest implements OperationRequest {
    private PersonalAcademicInfoDTO personalAcademicInfo;
    private String professionalDirection;
    private Double bulgarianLanguageGrade;
    private String secondExamSubject;
    private Double secondExamGrade;
    private BankingInfoDTO bankingInfo;
}
