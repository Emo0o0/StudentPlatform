package bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.apply;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetInsuranceApplyFormsRequest implements OperationRequest{

    private String studentId;
    private String specialty;
}
