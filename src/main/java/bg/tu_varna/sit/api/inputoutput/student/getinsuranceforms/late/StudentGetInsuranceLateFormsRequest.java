package bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.late;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetInsuranceLateFormsRequest implements OperationRequest {

    private String studentId;
    private String specialty;
}
