package bg.tu_varna.sit.persistence.repository.banking;

import bg.tu_varna.sit.persistence.entity.scholarship.banking.BankingInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BankingInfoRepository implements PanacheRepository<BankingInfo> {
}
