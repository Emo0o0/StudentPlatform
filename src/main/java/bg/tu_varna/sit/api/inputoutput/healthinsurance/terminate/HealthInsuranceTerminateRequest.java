package bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceTerminateRequest implements OperationRequest {

    private String terminationReason;
    private String schoolYear;

}
