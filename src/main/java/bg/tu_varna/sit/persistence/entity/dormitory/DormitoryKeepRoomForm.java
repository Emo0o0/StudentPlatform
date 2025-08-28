package bg.tu_varna.sit.persistence.entity.dormitory;

import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DormitoryKeepRoomForm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long formId;
    private Integer buildingNumber;
    private Integer roomNumber;
    @Enumerated(EnumType.STRING)
    private FormStatus formStatus;

}
