package bg.tu_varna.sit.persistence.repository.insurance;

import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceApplyForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class HealthInsuranceApplyFormRepository implements PanacheRepository<HealthInsuranceApplyForm> {

    public List<HealthInsuranceApplyForm> getStudentForms(Long studentId, String specialty) {
        List<HealthInsuranceApplyForm> forms = getEntityManager().createQuery("""
                        select f
                        from HealthInsuranceApplyForm f
                        join f.student s
                        join s.personalAcademicInfo p
                        where (:studentId is null or s.id = :studentId)
                          and (:specialty is null or p.specialty = :specialty)
                        """, HealthInsuranceApplyForm.class)
                .setParameter("studentId", studentId)     // Long or null
                .setParameter("specialty", specialty)     // String or null
                .getResultList();
        return forms;
    }
}
