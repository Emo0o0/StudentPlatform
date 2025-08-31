package bg.tu_varna.sit.persistence.repository.scholarship;

import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipApplyForm;
import bg.tu_varna.sit.persistence.entity.scholarship.ScholarshipType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ScholarshipApplyFormRepository implements PanacheRepository<ScholarshipApplyForm> {

    public List<ScholarshipApplyForm> getStudentForms(Long studentId, String specialty, ScholarshipType scholarshipType) {
        List<ScholarshipApplyForm> forms = getEntityManager().createQuery("""
                        select f
                        from ScholarshipApplyForm f
                        join f.student s
                        join s.personalAcademicInfo p
                        where (:studentId is null or s.id = :studentId)
                          and (:specialty is null or p.specialty = :specialty)
                          and (:type is null or f.scholarshipType= :type)
                        """, ScholarshipApplyForm.class)
                .setParameter("studentId", studentId)     // Long or null
                .setParameter("specialty", specialty)     // String or null
                .setParameter("type", scholarshipType)
                .getResultList();
        return forms;
    }

}
