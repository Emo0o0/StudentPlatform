package bg.tu_varna.sit.api.inputoutput.student.getaverages;

import bg.tu_varna.sit.api.contract.OperationResponse;
import bg.tu_varna.sit.api.inputoutput.student.StudentAverageDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentAveragesResponse implements OperationResponse {
    private List<StudentAverageDTO> studentAverages;
}
