package com.example.api.inputoutput.student.countfailed;

import com.example.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountFailedStudentsResponse implements OperationResponse {

    private long count;
}
