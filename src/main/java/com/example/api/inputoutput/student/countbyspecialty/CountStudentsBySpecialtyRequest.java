package com.example.api.inputoutput.student.countbyspecialty;

import com.example.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountStudentsBySpecialtyRequest implements OperationRequest {
    private String specialty;
}
