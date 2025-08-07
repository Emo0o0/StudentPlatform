package com.example.persistence.repository.scholarship.achievement;

import com.example.persistence.entity.scholarship.achievement.AchievementDocument;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AchievementDocumentRepository implements PanacheRepository<AchievementDocument> {
}
