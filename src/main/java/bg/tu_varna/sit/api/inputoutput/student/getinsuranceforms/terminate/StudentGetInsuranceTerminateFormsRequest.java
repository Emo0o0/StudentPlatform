package bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.terminate;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetInsuranceTerminateFormsRequest implements OperationRequest {

    private String studentId;
    private String specialty;
}
