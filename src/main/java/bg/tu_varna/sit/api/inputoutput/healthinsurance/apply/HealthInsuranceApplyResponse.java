package bg.tu_varna.sit.api.inputoutput.healthinsurance.apply;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;


@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceApplyResponse implements OperationResponse {
    private Boolean success;
}
