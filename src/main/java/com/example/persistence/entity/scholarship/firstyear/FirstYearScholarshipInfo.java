package com.example.persistence.entity.scholarship.firstyear;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class FirstYearScholarshipInfo {
    @Enumerated(EnumType.STRING)
    private ProfessionalDirection professionalDirection;
    private Double bulgarianLanguageGrade;
    private String secondExamSubject;
    private Double secondExamGrade;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private List<FirstYearDocument> firstYearDocuments = new ArrayList<>();
}
