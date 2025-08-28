package bg.tu_varna.sit.persistence.repository.scholarship.firstyear;

import bg.tu_varna.sit.persistence.entity.scholarship.firstyear.FirstYearScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FirstYearScholarshipInfoRepository implements PanacheRepository<FirstYearScholarshipInfo> {
}
