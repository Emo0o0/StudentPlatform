package bg.tu_varna.sit.api.inputoutput.student.getscholarshipforms;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialScholarshipDTO {
    private String socialScholarshipType;
    private boolean hasMarriage;
}
