package bg.tu_varna.sit.api.inputoutput.student.getinsuranceforms.apply;

import lombok.*;


@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetInsuranceApplyFormsResponse{

    private String formId;
    private String studentFirstName;
    private String studentLastName;
    private String studentFacultyNumber;

    private Boolean isReceivingWorkRelatedIncome;
    private Boolean isReceivingPension;
    private Boolean isReceivingOtherInsuredIncome;
    private String currentInsurer;
    private String formStatus;
    private String date;

}
