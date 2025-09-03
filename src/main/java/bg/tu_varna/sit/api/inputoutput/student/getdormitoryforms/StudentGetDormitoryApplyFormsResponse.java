package bg.tu_varna.sit.api.inputoutput.student.getdormitoryforms;

import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetDormitoryApplyFormsResponse {

    private String formId;
    private String studentFirstName;
    private String studentLastName;
    private String studentFacultyNumber;

    private List<FamilyMemberDTO> familyMembers;

    private boolean hasKeepRoomForm;
    private Integer keepRoomFormBuildingNumber;
    private Integer keepRoomFormRoomNumber;

    private String formStatus;

    private String date;

}
