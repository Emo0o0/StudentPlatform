package bg.tu_varna.sit.persistence.entity.insurance;

import bg.tu_varna.sit.persistence.entity.Student;
import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HealthInsuranceApplyForm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long formId;
    @Column(nullable = false)
    private Boolean isReceivingWorkRelatedIncome;
    @Column(nullable = false)
    private Boolean isReceivingPension;
    @Column(nullable = false)
    private Boolean isReceivingOtherInsuredIncome;
    @Column(nullable = false)
    private String currentInsurer;
    @CreationTimestamp
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private FormStatus formStatus;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_info_id")
    private PersonalAcademicInfo personalAcademicInfo;

    public void updateStatus(FormStatus status){
        this.formStatus=status;
    }
}
