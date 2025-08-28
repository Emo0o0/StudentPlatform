package bg.tu_varna.sit.api.inputoutput.scholarship.foreign;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForeignScholarshipApplyResponse implements OperationResponse {

    private Boolean success;
}
