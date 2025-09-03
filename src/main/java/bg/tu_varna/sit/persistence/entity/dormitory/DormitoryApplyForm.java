package bg.tu_varna.sit.persistence.entity.dormitory;

import bg.tu_varna.sit.persistence.entity.FamilyMember;
import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.Student;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DormitoryApplyForm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long formId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_info_id")
    private PersonalAcademicInfo personalAcademicInfo;
    private Integer buildingNumber;
    private Integer roomNumber;
    @ElementCollection
    private List<FamilyMember> familyMembers;
    @CreationTimestamp
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private FormStatus formStatus;
    @OneToOne
    @JoinColumn(name = "keep_room_form_id")
    private DormitoryKeepRoomForm keepRoomForm;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    public void updateStatus(FormStatus status){
        this.formStatus=status;
    }
}
