package bg.tu_varna.sit.api.inputoutput.student.getscholarshipforms;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstYearScholarshipDTO {
    private String bulgarianLanguageGrade;
    private String secondExamSubject;
    private String secondExamGrade;
}
