package com.example.persistence.entity.scholarship.meritincome;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class MeritWithIncomeScholarshipInfo {

    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;
    @Embedded
    private FamilyIncomeInfo familyIncomeInfo;
}
