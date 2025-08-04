package com.example.persistence.entity.scholarship.social;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class SocialScholarshipInfo {

    @Enumerated(EnumType.STRING)
    private SocialScholarshipType socialType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private List<SocialDocument> socialDocuments = new ArrayList<>();

    // For parents with children under 6
    private Boolean hasMarriage = false;
}
