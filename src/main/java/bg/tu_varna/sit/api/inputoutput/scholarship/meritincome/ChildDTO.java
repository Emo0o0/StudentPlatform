package bg.tu_varna.sit.api.inputoutput.scholarship.meritincome;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChildDTO {

    private String fullName;
    private LocalDate birthDate;
}
