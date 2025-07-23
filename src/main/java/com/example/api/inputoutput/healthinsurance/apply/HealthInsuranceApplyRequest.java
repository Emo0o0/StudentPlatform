package com.example.api.inputoutput.healthinsurance.apply;

import com.example.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceApplyRequest implements OperationRequest {

    private Boolean isReceivingWorkRelatedIncome;
    private Boolean isReceivingPension;
    private Boolean isReceivingOtherInsuredIncome;
    private String currentInsurer;
}
