package bg.tu_varna.sit.api.inputoutput.mark.avgbyspecialty;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AverageMarkBySpecRequest implements OperationRequest {

    private String subject;
}
