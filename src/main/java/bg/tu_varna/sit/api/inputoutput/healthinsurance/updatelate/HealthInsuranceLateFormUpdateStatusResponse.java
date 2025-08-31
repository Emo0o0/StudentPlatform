package bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceLateFormUpdateStatusResponse implements OperationResponse {
    private boolean success;
}
