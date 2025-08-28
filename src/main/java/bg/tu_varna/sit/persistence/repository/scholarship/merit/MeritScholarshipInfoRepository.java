package bg.tu_varna.sit.persistence.repository.scholarship.merit;

import bg.tu_varna.sit.persistence.entity.scholarship.merit.MeritScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeritScholarshipInfoRepository implements PanacheRepository<MeritScholarshipInfo> {
}
