package bg.tu_varna.sit.persistence.repository;

import bg.tu_varna.sit.persistence.entity.Student;
import bg.tu_varna.sit.persistence.entity.StudentStatus;
import bg.tu_varna.sit.api.inputoutput.student.StudentStatsDTO;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    public Student findByEmail(String email) {
        return find("personalAcademicInfo.email", email).firstResult();
    }

    public Optional<Student> findByFacultyNumber(String facultyNumber) {
        return find("personalAcademicInfo.facultyNumber", facultyNumber).firstResultOptional();
    }

    //~~~~~~~~~~~~~~~~~~~~~~

    public List<StudentStatsDTO> getStudentStatistics(Faculty faculty, String department, String specialty) {
        List<Object[]> results = getEntityManager().createQuery(
                        "select " +
                        "p.specialty, " +
                        "count(distinct s.id), " +
                        "count(distinct case when p.studentStatus = 'INTERRUPTED' then s.id end), " +
                        "count(distinct case when p.studentStatus = 'REINSTATED' then s.id end), " +
                        "count(distinct case when p.studentStatus = 'GRADUATED' then s.id end), " +
                        "count(distinct case when m.hasAttendedExam = false then s.id end), " +
                        "count(distinct case when m.mark = '2' then s.id end) " +
                        "from Student s " +
                        "join s.personalAcademicInfo p " +
                        "left join s.marks m " +
                        "where (:faculty is null or p.faculty = :faculty) " +
                        "and (:department is null or p.department = :department) " +
                        "and (:specialty is null or p.specialty = :specialty) " +
                        "group by p.specialty",
                        Object[].class)
                .setParameter("faculty", faculty)
                .setParameter("department", department)
                .setParameter("specialty", specialty)
                .getResultList();

        return results.stream()
                .map(r -> StudentStatsDTO.builder()
                        .specialty((String) r[0])
                        .total((long) r[1])
                        .interrupted((long) r[2])
                        .reinstated((long) r[3])
                        .graduated((long) r[4])
                        .notAttended((long) r[5])
                        .withFail((long) r[6])
                        .build())
                .toList();
    }
}
