package bg.tu_varna.sit.persistence.repository;

import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonalAcademicInfoRepository implements PanacheRepository<PersonalAcademicInfo> {
}
