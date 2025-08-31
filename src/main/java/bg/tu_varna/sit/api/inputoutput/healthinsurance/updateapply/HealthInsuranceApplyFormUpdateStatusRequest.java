package bg.tu_varna.sit.api.inputoutput.healthinsurance.updateapply;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceApplyFormUpdateStatusRequest implements OperationRequest {

    private String formId;
    private String formStatus;
}
