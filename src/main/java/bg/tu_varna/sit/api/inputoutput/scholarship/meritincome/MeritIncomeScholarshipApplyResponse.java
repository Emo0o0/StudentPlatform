package bg.tu_varna.sit.api.inputoutput.scholarship.meritincome;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeritIncomeScholarshipApplyResponse implements OperationResponse {

    private Boolean success;
}
