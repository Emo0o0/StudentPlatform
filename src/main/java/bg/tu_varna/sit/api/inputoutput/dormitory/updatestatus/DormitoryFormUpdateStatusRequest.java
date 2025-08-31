package bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus;

import bg.tu_varna.sit.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryFormUpdateStatusRequest implements OperationRequest {

    private String formId;
    private String status;
}
