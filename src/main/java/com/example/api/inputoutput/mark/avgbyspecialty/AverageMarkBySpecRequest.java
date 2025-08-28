package com.example.api.inputoutput.mark.avgbyspecialty;

import com.example.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AverageMarkBySpecRequest implements OperationRequest {

    private String subject;
}
