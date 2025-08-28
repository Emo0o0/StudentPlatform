package bg.tu_varna.sit.api.inputoutput.student;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentAverageDTO {
    private Long studentId;
    private double averageGrade;
}
