package bg.tu_varna.sit.api.inputoutput.student.getscholarshipforms;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetScholarshipFormsListResponse implements OperationResponse {

    private List<StudentGetScholarshipFormsResponse> forms;
}
