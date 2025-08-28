package bg.tu_varna.sit.api.inputoutput.healthinsurance.late;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceLateResponse implements OperationResponse {

    private Boolean success;
}
