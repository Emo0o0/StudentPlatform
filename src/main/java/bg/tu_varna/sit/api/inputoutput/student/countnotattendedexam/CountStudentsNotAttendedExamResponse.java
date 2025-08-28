package bg.tu_varna.sit.api.inputoutput.student.countnotattendedexam;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountStudentsNotAttendedExamResponse implements OperationResponse {

    private long count;
}
