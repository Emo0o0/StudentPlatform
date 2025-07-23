package com.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String fullName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String facultyNumber;
    private String specialty;
    private Integer groupNumber;
    private Character subGroup;
    private String academicYear;
    //more personal info
    private Integer uniYear;
    private String placeOfResidence;
    private String streetName;
    private Integer streetNumber;
    private String entrance;
    private Integer floor;
    private Integer flatNumber;
    @Column(unique = true)
    private String egn;
    @Column(unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthInsuranceApplyForm> healthInsuranceApplyForms;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthInsuranceLateForm> healthInsuranceLateForms;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthInsuranceTerminateForm> healthInsuranceTerminateForms;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DormitoryApplyForm> dormitoryApplyForms;

}
