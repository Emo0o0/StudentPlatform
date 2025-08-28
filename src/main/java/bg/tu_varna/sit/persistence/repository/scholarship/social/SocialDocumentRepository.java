package bg.tu_varna.sit.persistence.repository.scholarship.social;

import bg.tu_varna.sit.persistence.entity.scholarship.social.SocialDocument;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SocialDocumentRepository implements PanacheRepository<SocialDocument> {
}
