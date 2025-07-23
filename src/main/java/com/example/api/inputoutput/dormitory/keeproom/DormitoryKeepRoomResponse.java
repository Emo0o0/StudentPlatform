package com.example.api.inputoutput.dormitory.keeproom;

import com.example.api.contract.OperationResponse;
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
