package com.example.api.inputoutput.student.countnotattendedexam;

import com.example.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountStudentsNotAttendedExamRequest implements OperationRequest {

    private String faculty;
    private String department;
    private String specialty;
}
