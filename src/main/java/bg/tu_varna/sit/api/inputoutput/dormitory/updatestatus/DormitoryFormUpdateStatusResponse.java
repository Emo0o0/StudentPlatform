package bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryFormUpdateStatusResponse implements OperationResponse {

    private boolean success;
}
