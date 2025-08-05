package com.example.persistence.entity;

import com.example.persistence.entity.enums.CourseYear;
import com.example.persistence.entity.enums.DegreeLevel;
import com.example.persistence.entity.enums.Faculty;
import com.example.persistence.entity.enums.Semester;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String firstName;
    private String secondName;
    private String lastName;
    private String egn;
    private String address;
    private String phoneNumber;
    private String facultyNumber;
    //academic info
    @Enumerated(EnumType.STRING)
    private CourseYear courseYear;
    @Enumerated(EnumType.STRING)
    private Semester semester;
    @Enumerated(EnumType.STRING)
    private DegreeLevel degreeLevel;
    @Enumerated(EnumType.STRING)
    private Faculty faculty;
    private String specialization;


}
