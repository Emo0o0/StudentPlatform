package com.example.persistence.repository.scholarship.achievement;

import com.example.persistence.entity.scholarship.achievement.SpecialAchievementScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpecialAchievementScholarshipInfoRepository implements PanacheRepository<SpecialAchievementScholarshipInfo> {
}
