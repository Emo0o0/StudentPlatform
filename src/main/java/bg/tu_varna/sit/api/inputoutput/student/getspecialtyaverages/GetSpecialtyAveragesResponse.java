package bg.tu_varna.sit.api.inputoutput.student.getspecialtyaverages;

import bg.tu_varna.sit.api.contract.OperationResponse;
import bg.tu_varna.sit.api.inputoutput.student.SpecialtyAverageDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSpecialtyAveragesResponse implements OperationResponse {
    private List<SpecialtyAverageDTO> specialtyAverages;
}
