package bg.tu_varna.sit.api.inputoutput.mark.countmarksbysubject;

import bg.tu_varna.sit.api.contract.OperationRequest;
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
