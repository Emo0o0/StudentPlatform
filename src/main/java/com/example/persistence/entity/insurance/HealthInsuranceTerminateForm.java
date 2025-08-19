package com.example.persistence.entity.insurance;

import com.example.persistence.entity.PersonalAcademicInfo;
import com.example.persistence.entity.Student;
import com.example.persistence.entity.enums.FormStatus;
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
public class HealthInsuranceTerminateForm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long formId;
    @Column(nullable = false, length = 9)
    private String schoolYear;
    @Column(nullable = false)
    private String terminationReason;
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
}
