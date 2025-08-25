package com.example.api.inputoutput.student.countnotattendedexam;

import com.example.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountStudentsNotAttendedExamResponse implements OperationResponse {

    private long count;
}
