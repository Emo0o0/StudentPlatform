package bg.tu_varna.sit.persistence.entity;

import bg.tu_varna.sit.persistence.entity.enums.CourseYear;
import bg.tu_varna.sit.persistence.entity.enums.DegreeLevel;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import bg.tu_varna.sit.persistence.entity.enums.Semester;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonalAcademicInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    //personal info
    private String email;
    private String firstName;
    private String secondName;
    private String lastName;
    private String egn;
    private String address;
    private String phoneNumber;
    private String placeOfResidence;
    private String streetName;
    private Integer streetNumber;
    private String entrance;
    private Integer floor;
    private Integer flatNumber;
    //academic info
    private String facultyNumber;
    @Enumerated(EnumType.STRING)
    private CourseYear courseYear;
    @Enumerated(EnumType.STRING)
    private Semester semester;
    @Enumerated(EnumType.STRING)
    private DegreeLevel degreeLevel;
    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;
    @Enumerated(EnumType.STRING)
    private Faculty faculty;
    private String department;
    private String specialty;
    private Integer studentGroup;
    private Character subGroup;


    public PersonalAcademicInfo copy() {
        return PersonalAcademicInfo.builder()
                .id(this.id)
                .email(this.email)
                .firstName(this.firstName)
                .secondName(this.secondName)
                .lastName(this.lastName)
                .egn(this.egn)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .placeOfResidence(this.placeOfResidence)
                .streetName(this.streetName)
                .streetNumber(this.streetNumber)
                .entrance(this.entrance)
                .floor(this.floor)
                .flatNumber(this.flatNumber)
                .facultyNumber(this.facultyNumber)
                .courseYear(this.courseYear)
                .semester(this.semester)
                .degreeLevel(this.degreeLevel)
                .studentStatus(this.studentStatus)
                .faculty(this.faculty)
                .department(this.department)
                .specialty(this.specialty)
                .studentGroup(this.studentGroup)
                .subGroup(this.subGroup)
                .build();
    }

}

