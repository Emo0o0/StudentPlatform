package bg.tu_varna.sit.api.inputoutput.student.countfailed;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountFailedStudentsRequest implements OperationRequest {

    private String faculty;
    private String department;
    private String specialty;
}
