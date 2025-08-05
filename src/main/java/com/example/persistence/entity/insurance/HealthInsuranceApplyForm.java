package com.example.persistence.entity.insurance;

import com.example.persistence.entity.Student;
import com.example.persistence.entity.enums.FormStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

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
    private Timestamp date;
    @Enumerated(EnumType.STRING)
    private FormStatus formStatus;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
