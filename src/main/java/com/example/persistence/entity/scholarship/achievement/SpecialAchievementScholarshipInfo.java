package com.example.persistence.entity.scholarship.achievement;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class SpecialAchievementScholarshipInfo {
    private Boolean applyForSpecialAchievement = false;
    private String achievementTopic;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private List<AchievementDocument> achievementDocuments = new ArrayList<>();
}
