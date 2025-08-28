package bg.tu_varna.sit.persistence.repository.scholarship.meritincome;

import bg.tu_varna.sit.persistence.entity.scholarship.meritincome.IncomeDocument;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IncomeDocumentRepository implements PanacheRepository<IncomeDocument> {
}
