package bg.tu_varna.sit.persistence.repository.scholarship;

import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipApplyForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScholarshipApplyFormRepository implements PanacheRepository<ScholarshipApplyForm> {
}
