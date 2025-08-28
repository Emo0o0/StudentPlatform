package bg.tu_varna.sit.api.inputoutput.mark.countmarksbysubject;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountMarksBySubjectResponse implements OperationResponse {

    private long count;
}
