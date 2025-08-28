package com.example.api.inputoutput.mark.avgbyspecialty;

import com.example.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AverageMarkBySpecResponse implements OperationResponse {

    private Double avgGrade;
}
