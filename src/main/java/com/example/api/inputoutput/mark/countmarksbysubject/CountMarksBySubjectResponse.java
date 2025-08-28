package com.example.api.inputoutput.mark.countmarksbysubject;

import com.example.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountMarksBySubjectResponse implements OperationResponse {

    private long count;
}
