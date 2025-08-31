package bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.terminate;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

import java.util.List;
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetInsuranceTerminateFormsListResponse implements OperationResponse {
    private List<StudentGetInsuranceTerminateFormsResponse> forms;
}
