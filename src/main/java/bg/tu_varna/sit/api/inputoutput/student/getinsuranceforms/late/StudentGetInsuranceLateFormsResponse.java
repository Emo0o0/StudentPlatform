package bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.late;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StudentGetInsuranceLateFormsResponse {
    private String formId;
    private String studentFirstName;
    private String studentLastName;
    private String studentFacultyNumber;
    private String year;
    private String formStatus;
    private String date;
}
