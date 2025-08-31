package bg.tu_varna.sit.api.inputoutput.student.getscholarshipforms;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetScholarshipFormsRequest implements OperationRequest {

    private String studentId;
    private String specialty;
    private String scholarshipType;

}
