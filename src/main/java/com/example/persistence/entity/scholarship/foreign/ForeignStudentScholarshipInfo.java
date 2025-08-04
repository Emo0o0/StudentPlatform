package com.example.persistence.entity.scholarship.foreign;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ForeignStudentScholarshipInfo {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private List<ForeignStudentDocument> foreignStudentDocuments = new ArrayList<>();
}
