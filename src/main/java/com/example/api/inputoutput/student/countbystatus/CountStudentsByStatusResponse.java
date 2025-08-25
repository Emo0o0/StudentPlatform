package com.example.api.inputoutput.student.countbystatus;

import com.example.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountStudentsByStatusResponse implements OperationResponse {
    private long count;
}
