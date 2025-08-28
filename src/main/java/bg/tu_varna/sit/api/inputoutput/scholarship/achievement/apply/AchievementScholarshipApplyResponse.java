package bg.tu_varna.sit.api.inputoutput.scholarship.achievement.apply;

import bg.tu_varna.sit.api.contract.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementScholarshipApplyResponse implements OperationResponse {

    private Boolean success;
}
