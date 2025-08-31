package bg.tu_varna.sit.api.inputoutput.student.getdormitoryforms;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetDormitoryApplyFormsRequest implements OperationRequest {

    private String studentId;
    private String specialty;
}
