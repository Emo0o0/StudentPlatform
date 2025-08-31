package bg.tu_varna.sit.persistence.repository.insurance;

import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceLateForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class HealthInsuranceLateFormRepository implements PanacheRepository<HealthInsuranceLateForm> {

    public List<HealthInsuranceLateForm> getStudentForms(Long studentId, String specialty) {
        List<HealthInsuranceLateForm> forms = getEntityManager().createQuery("""
                        select f
                        from HealthInsuranceLateForm f
                        join f.student s
                        join s.personalAcademicInfo p
                        where (:studentId is null or s.id = :studentId)
                          and (:specialty is null or p.specialty = :specialty)
                        """, HealthInsuranceLateForm.class)
                .setParameter("studentId", studentId)     // Long or null
                .setParameter("specialty", specialty)     // String or null
                .getResultList();
        return forms;
    }
}
