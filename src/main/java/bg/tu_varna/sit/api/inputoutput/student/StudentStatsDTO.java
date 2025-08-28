package bg.tu_varna.sit.api.inputoutput.student;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentStatsDTO {

    private String specialty;
    private long total;
    private long interrupted;
    private long reinstated;
    private long graduated;
    private long notAttended;
    private long withFail;
}
