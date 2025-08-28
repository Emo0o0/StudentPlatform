package bg.tu_varna.sit.api.inputoutput.dormitory.keeproom;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryKeepRoomResponse implements OperationResponse {

    private Long formId;
    private Boolean success;

}
