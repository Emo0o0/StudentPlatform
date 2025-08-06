package com.example.persistence.entity.dormitory;

import com.example.persistence.entity.FamilyMember;
import com.example.persistence.entity.Student;
import com.example.persistence.entity.enums.DegreeLevel;
import com.example.persistence.entity.enums.FormStatus;
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
    @Enumerated(EnumType.STRING)
    private DegreeLevel degreeLevel;
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
}
