package bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.apply;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetInsuranceApplyFormsListResponse implements OperationResponse {

    private List<StudentGetInsuranceApplyFormsResponse> forms;
}
