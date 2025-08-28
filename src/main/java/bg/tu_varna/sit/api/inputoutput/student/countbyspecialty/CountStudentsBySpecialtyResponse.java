package bg.tu_varna.sit.api.inputoutput.student.countbyspecialty;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountStudentsBySpecialtyResponse implements OperationResponse {
    private long count;
}
