package com.example.api.inputoutput.student.countbyspecialty;

import com.example.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountStudentsBySpecialtyResponse implements OperationResponse {
    private long count;
}
