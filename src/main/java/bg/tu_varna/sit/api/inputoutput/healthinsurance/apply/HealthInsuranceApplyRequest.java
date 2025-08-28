package bg.tu_varna.sit.api.inputoutput.healthinsurance.apply;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceApplyRequest implements OperationRequest {

    private Boolean isReceivingWorkRelatedIncome;
    private Boolean isReceivingPension;
    private Boolean isReceivingOtherInsuredIncome;
    private String currentInsurer;
}
