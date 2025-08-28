package com.example.api.inputoutput.mark.countmarksbysubject;

import com.example.api.contract.OperationRequest;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountMarksBySubjectRequest implements OperationRequest {

    private String subject;
    private String mark;
}
