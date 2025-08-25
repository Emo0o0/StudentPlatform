package com.example.persistence.entity;

import com.example.persistence.entity.dormitory.DormitoryApplyForm;
import com.example.persistence.entity.insurance.HealthInsuranceApplyForm;
import com.example.persistence.entity.insurance.HealthInsuranceLateForm;
import com.example.persistence.entity.insurance.HealthInsuranceTerminateForm;
import com.example.persistence.entity.scholarship.ScholarshipApplyForm;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info_id", referencedColumnName = "id")
    private PersonalAcademicInfo personalAcademicInfo;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mark> marks = new ArrayList<>();


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthInsuranceApplyForm> healthInsuranceApplyForms;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthInsuranceLateForm> healthInsuranceLateForms;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthInsuranceTerminateForm> healthInsuranceTerminateForms;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DormitoryApplyForm> dormitoryApplyForms;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScholarshipApplyForm> scholarshipApplyForms;


}
