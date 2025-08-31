package bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceTerminateFormUpdateStatusResponse implements OperationResponse {
    private boolean success;
}
