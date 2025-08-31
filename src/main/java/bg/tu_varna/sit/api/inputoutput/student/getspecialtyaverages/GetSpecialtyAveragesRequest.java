package bg.tu_varna.sit.api.inputoutput.student.getspecialtyaverages;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSpecialtyAveragesRequest implements OperationRequest {
    private String specialty;
}
