package bg.tu_varna.sit.api.inputoutput.scholarship.merit;

import bg.tu_varna.sit.api.contract.OperationRequest;
import bg.tu_varna.sit.api.PersonalAcademicInfoDTO;
import bg.tu_varna.sit.api.inputoutput.scholarship.BankingInfoDTO;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeritScholarshipApplyRequest implements OperationRequest {
    private PersonalAcademicInfoDTO personalAcademicInfo;
    private BankingInfoDTO bankingInfo;
    private Double previousGPA;
}
