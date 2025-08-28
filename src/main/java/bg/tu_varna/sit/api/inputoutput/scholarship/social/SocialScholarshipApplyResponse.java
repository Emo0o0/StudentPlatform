package bg.tu_varna.sit.api.inputoutput.scholarship.social;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialScholarshipApplyResponse implements OperationResponse {

    private Boolean success;
}
