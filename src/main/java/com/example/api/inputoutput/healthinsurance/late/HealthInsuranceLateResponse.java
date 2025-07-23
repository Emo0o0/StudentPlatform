package com.example.api.inputoutput.healthinsurance.late;

import com.example.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceLateResponse implements OperationResponse {

    private Boolean success;
}
