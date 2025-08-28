package bg.tu_varna.sit.api.inputoutput.scholarship.social;

import bg.tu_varna.sit.api.PersonalAcademicInfoDTO;
import bg.tu_varna.sit.api.contract.OperationRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.BankingInfoDTO;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialScholarshipApplyRequest implements OperationRequest {
    private PersonalAcademicInfoDTO personalAcademicInfo;
    private String socialType;
    private Boolean hasMarriage;
    private BankingInfoDTO bankingInfo;
}
