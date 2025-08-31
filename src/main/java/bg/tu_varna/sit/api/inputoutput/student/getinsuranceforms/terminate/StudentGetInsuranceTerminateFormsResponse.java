package bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.terminate;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetInsuranceTerminateFormsResponse {
    private String formId;
    private String studentFirstName;
    private String studentLastName;
    private String studentFacultyNumber;
    private String date;
    private String terminationReason;
    private String formStatus;
}
