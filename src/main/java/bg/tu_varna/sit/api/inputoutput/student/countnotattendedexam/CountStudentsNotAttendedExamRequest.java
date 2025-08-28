package bg.tu_varna.sit.api.inputoutput.student.countnotattendedexam;

import bg.tu_varna.sit.api.contract.OperationRequest;
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
