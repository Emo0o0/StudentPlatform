package bg.tu_varna.sit.api.inputoutput.scholarship.merit;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeritScholarshipApplyResponse implements OperationResponse {

    private Boolean success;
}
