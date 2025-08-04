package com.example.persistence.entity.scholarship;

import com.example.persistence.entity.PersonalInfo;
import com.example.persistence.entity.scholarship.achievement.SpecialAchievementScholarshipInfo;
import com.example.persistence.entity.scholarship.banking.BankingInfo;
import com.example.persistence.entity.scholarship.firstyear.FirstYearScholarshipInfo;
import com.example.persistence.entity.scholarship.foreign.ForeignStudentScholarshipInfo;
import com.example.persistence.entity.scholarship.merit.MeritScholarshipInfo;
import com.example.persistence.entity.scholarship.meritincome.MeritWithIncomeScholarshipInfo;
import com.example.persistence.entity.scholarship.social.SocialScholarshipInfo;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScholarshipApplyForm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long formId;
    @Embedded
    private PersonalInfo personalInfo;
    //успех от предходните 2 семестъра
    private Double previousGPA;
    @Enumerated(EnumType.STRING)
    private ScholarshipType scholarshipType;

    @Embedded
    private MeritScholarshipInfo meritScholarshipInfo;

    @Embedded
    private MeritWithIncomeScholarshipInfo meritWithIncomeInfo;

    @Embedded
    private SocialScholarshipInfo socialScholarshipInfo;

    @Embedded
    private FirstYearScholarshipInfo firstYearScholarshipInfo;

    @Embedded
    private ForeignStudentScholarshipInfo foreignStudentScholarshipInfo;
    @Embedded
    private SpecialAchievementScholarshipInfo specialAchievementScholarshipInfo;

    @Embedded
    private BankingInfo bankingInfo;

}
