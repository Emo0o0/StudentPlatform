package com.example.api.inputoutput.healthinsurance.apply;

import com.example.api.contract.OperationResponse;
import lombok.*;


@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceApplyResponse implements OperationResponse {
    private Boolean success;
}
