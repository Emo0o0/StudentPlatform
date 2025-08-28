package bg.tu_varna.sit.persistence.repository.scholarship.social;

import bg.tu_varna.sit.persistence.entity.scholarship.social.SocialScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SocialScholarshipInfoRepository implements PanacheRepository<SocialScholarshipInfo> {
}
