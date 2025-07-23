package com.example.api.inputoutput.healthinsurance.late;

import com.example.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceLateRequest implements OperationRequest {

    private String schoolYear;
}
