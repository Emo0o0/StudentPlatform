package com.example.persistence.entity;

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
