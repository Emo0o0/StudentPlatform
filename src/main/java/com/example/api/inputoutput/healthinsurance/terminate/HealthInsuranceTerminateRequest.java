package com.example.api.inputoutput.healthinsurance.terminate;

import com.example.api.contract.OperationRequest;
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
