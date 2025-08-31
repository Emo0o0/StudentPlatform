package bg.tu_varna.sit.api.inputoutput.student.getaverages;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentAveragesRequest implements OperationRequest {
    private String faculty;
    private String department;
    private String specialty;
}
