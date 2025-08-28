package bg.tu_varna.sit.persistence.repository.scholarship.achievement;

import bg.tu_varna.sit.persistence.entity.scholarship.achievement.SpecialAchievementScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpecialAchievementScholarshipInfoRepository implements PanacheRepository<SpecialAchievementScholarshipInfo> {
}
