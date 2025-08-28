package bg.tu_varna.sit.api.inputoutput.student.countbyspecialty;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountStudentsBySpecialtyRequest implements OperationRequest {
    private String specialty;
}
