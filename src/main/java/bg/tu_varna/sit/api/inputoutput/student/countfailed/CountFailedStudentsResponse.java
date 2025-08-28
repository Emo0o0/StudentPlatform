package bg.tu_varna.sit.api.inputoutput.student.countfailed;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountFailedStudentsResponse implements OperationResponse {

    private long count;
}
