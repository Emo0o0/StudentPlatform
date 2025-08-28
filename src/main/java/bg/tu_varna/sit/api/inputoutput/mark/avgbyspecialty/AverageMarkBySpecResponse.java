package bg.tu_varna.sit.api.inputoutput.mark.avgbyspecialty;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AverageMarkBySpecResponse implements OperationResponse {

    private Double avgGrade;
}
