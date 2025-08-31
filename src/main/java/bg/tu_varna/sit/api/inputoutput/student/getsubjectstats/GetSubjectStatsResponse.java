package bg.tu_varna.sit.api.inputoutput.student.getsubjectstats;

import bg.tu_varna.sit.api.contract.OperationResponse;
import bg.tu_varna.sit.api.inputoutput.student.SubjectStatsDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubjectStatsResponse implements OperationResponse {
    private List<SubjectStatsDTO> subjectStats;
}
