package com.example.persistence.entity;

import com.example.persistence.entity.enums.CourseYear;
import com.example.persistence.entity.enums.DegreeLevel;
import com.example.persistence.entity.enums.Faculty;
import com.example.persistence.entity.enums.Semester;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PersonalInfo {

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
