package bg.tu_varna.sit.persistence.repository.insurance;

import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceTerminateForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class HealthInsuranceTerminateFormRepository implements PanacheRepository<HealthInsuranceTerminateForm> {

    public List<HealthInsuranceTerminateForm> getStudentForms(Long studentId, String specialty) {
        List<HealthInsuranceTerminateForm> forms = getEntityManager().createQuery("""
                        select f
                        from HealthInsuranceTerminateForm f
                        join f.student s
                        join s.personalAcademicInfo p
                        where (:studentId is null or s.personalAcademicInfo.facultyNumber = :studentId)
                          and (:specialty is null or p.specialty = :specialty)
                        """, HealthInsuranceTerminateForm.class)
                .setParameter("studentId", studentId)     // Long or null
                .setParameter("specialty", specialty)     // String or null
                .getResultList();
        return forms;
    }
}
