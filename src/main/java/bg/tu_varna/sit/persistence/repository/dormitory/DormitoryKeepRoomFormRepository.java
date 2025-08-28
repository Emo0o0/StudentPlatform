package bg.tu_varna.sit.persistence.repository.dormitory;

import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryKeepRoomForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DormitoryKeepRoomFormRepository implements PanacheRepository<DormitoryKeepRoomForm> {
}
