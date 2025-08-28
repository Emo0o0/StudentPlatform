package bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceTerminateResponse implements OperationResponse {

    private Boolean success;
}
