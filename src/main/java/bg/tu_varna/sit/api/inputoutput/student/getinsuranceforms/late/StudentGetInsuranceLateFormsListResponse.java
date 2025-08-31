package bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.late;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetInsuranceLateFormsListResponse implements OperationResponse {
    private List<StudentGetInsuranceLateFormsResponse> forms;
}
