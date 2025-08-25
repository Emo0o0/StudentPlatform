package com.example.api.inputoutput.student.countbystatus;

import com.example.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountStudentsByStatusRequest implements OperationRequest {
    private String studentStatus;
    private String faculty;
    private String department;
    private String specialty;
}
