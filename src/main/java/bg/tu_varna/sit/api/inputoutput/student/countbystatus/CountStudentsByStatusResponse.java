package bg.tu_varna.sit.api.inputoutput.student.countbystatus;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountStudentsByStatusResponse implements OperationResponse {
    private long count;
}
