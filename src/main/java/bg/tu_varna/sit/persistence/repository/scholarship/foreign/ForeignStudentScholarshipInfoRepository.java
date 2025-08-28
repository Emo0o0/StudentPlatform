package bg.tu_varna.sit.persistence.repository.scholarship.foreign;

import bg.tu_varna.sit.persistence.entity.scholarship.foreign.ForeignStudentScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ForeignStudentScholarshipInfoRepository implements PanacheRepository<ForeignStudentScholarshipInfo> {
}
