package bg.tu_varna.sit.api.inputoutput.healthinsurance.updateapply;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceApplyFormUpdateStatusResponse implements OperationResponse {

    private boolean success;
}
