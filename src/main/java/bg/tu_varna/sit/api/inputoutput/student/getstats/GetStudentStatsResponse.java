package bg.tu_varna.sit.api.inputoutput.student.getstats;

import bg.tu_varna.sit.api.contract.OperationResponse;
import bg.tu_varna.sit.api.inputoutput.student.StudentStatsDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentStatsResponse implements OperationResponse {
    private List<StudentStatsDTO> studentStats;
}
