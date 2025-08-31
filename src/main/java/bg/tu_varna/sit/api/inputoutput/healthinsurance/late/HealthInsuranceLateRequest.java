package bg.tu_varna.sit.api.inputoutput.healthinsurance.late;

import bg.tu_varna.sit.api.PersonalAcademicInfoDTO;
import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceLateRequest implements OperationRequest {

    private PersonalAcademicInfoDTO personalAcademicInfo;
    private String schoolYear;
}
