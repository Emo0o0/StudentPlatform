package bg.tu_varna.sit.api.inputoutput.scholarship.updatestatus;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipFormUpdateStatusRequest implements OperationRequest {
    private String formId;
    private String formStatus;
}
