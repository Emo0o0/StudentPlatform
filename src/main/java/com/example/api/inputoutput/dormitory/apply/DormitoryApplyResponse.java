package com.example.api.inputoutput.dormitory.apply;

import com.example.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryApplyResponse implements OperationResponse {
    private Boolean success;
}
