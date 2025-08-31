package bg.tu_varna.sit.api.inputoutput.scholarship.updatestatus;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipFormUpdateStatusResponse implements OperationResponse {
    private boolean success;
}
