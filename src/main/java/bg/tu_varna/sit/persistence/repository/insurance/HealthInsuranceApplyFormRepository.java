package bg.tu_varna.sit.persistence.repository.insurance;

import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceApplyForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HealthInsuranceApplyFormRepository implements PanacheRepository<HealthInsuranceApplyForm> {
}
