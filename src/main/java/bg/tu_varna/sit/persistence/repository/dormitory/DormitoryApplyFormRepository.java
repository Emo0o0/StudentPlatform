package bg.tu_varna.sit.persistence.repository.dormitory;

import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryApplyForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DormitoryApplyFormRepository implements PanacheRepository<DormitoryApplyForm> {

    public List<DormitoryApplyForm> getStudentForms(Long studentId, String specialty) {
        List<DormitoryApplyForm> forms = getEntityManager().createQuery("""
                        select f
                        from DormitoryApplyForm f
                        join f.student s
                        join s.personalAcademicInfo p
                        where (:studentId is null or s.id = :studentId)
                          and (:specialty is null or p.specialty = :specialty)
                        """, DormitoryApplyForm.class)
                .setParameter("studentId", studentId)     // Long or null
                .setParameter("specialty", specialty)     // String or null
                .getResultList();
        return forms;
    }

}
