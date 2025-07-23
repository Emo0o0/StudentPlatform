package com.example.api.inputoutput.dormitory.keeproom;

import com.example.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryKeepRoomRequest implements OperationRequest {
    private int buildingNumber;
    private int roomNumber;
}
