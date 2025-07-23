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
public class HealthInsuranceTerminateForm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long formId;
    @Column(nullable = false, length = 9)
    private String schoolYear;
    @Column(nullable = false)
    private String terminationReason;
    @CreationTimestamp
    private Timestamp date;
    @Enumerated(EnumType.STRING)
    private FormStatus formStatus;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
