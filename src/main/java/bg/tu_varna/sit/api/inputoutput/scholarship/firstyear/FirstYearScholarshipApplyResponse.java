package bg.tu_varna.sit.api.inputoutput.scholarship.firstyear;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstYearScholarshipApplyResponse implements OperationResponse {

    private Boolean success;
}
