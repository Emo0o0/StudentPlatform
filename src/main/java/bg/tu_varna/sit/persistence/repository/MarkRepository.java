package bg.tu_varna.sit.persistence.repository;

import bg.tu_varna.sit.api.inputoutput.student.SpecialtyAverageDTO;
import bg.tu_varna.sit.api.inputoutput.student.StudentAverageDTO;
import bg.tu_varna.sit.api.inputoutput.student.SubjectStatsDTO;
import bg.tu_varna.sit.persistence.entity.Mark;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class MarkRepository implements PanacheRepository<Mark> {

    // Среден успех по дисциплина (без неявили и без незаверени)
    public Double avgGradeBySubject(String subject) {
        List<Integer> grades = find("subject = ?1 and hasAttendedExam = true and subjectStatus = 'ELIGIBLE' and mark in ('2','3','4','5','6')", subject)
                .stream()
                .map(m -> Integer.parseInt(((Mark) m).getMark()))
                .toList();

        if (!grades.isEmpty()) {
            return grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }

        long total = count("subject = ?1", subject);
        long eligible = count("subject = ?1 and subjectStatus = 'ELIGIBLE'", subject);

        if (total == 0) return 0.0;

        return (eligible * 100.0) / total;
    }


    // Брой по оценка
    public long countByMark(String subject, String mark) {
        return count("subject = ?1 and mark = ?2", subject, mark);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public List<SubjectStatsDTO> getSubjectStatisticsBySpecialty(Faculty faculty, String department, String specialty) {
        StringBuilder jpql = new StringBuilder("""
        SELECT 
            m.subject,
            CASE 
                WHEN COUNT(CASE WHEN m.hasAttendedExam = true 
                                 AND m.subjectStatus = 'ELIGIBLE' 
                                 AND m.mark IN ('2','3','4','5','6') 
                           THEN 1 END) > 0
                THEN AVG(CASE 
                            WHEN m.hasAttendedExam = true 
                             AND m.subjectStatus = 'ELIGIBLE' 
                             AND m.mark IN ('2','3','4','5','6') 
                         THEN CAST(m.mark AS INTEGER) 
                         END)
                ELSE (100.0 * COUNT(CASE 
                                     WHEN m.hasAttendedExam = true 
                                      AND m.subjectStatus = 'ELIGIBLE' 
                                      AND m.mark = 'Зачита се' 
                                    THEN 1 END)
                       / NULLIF(COUNT(CASE 
                                         WHEN m.mark IN ('Зачита се','Не се зачита') 
                                      THEN 1 END), 0))
            END,
            COUNT(CASE WHEN m.mark = '6' THEN 1 END),
            COUNT(CASE WHEN m.mark = '5' THEN 1 END),
            COUNT(CASE WHEN m.mark = '4' THEN 1 END),
            COUNT(CASE WHEN m.mark = '3' THEN 1 END),
            COUNT(CASE WHEN m.mark = '2' THEN 1 END),
            COUNT(CASE WHEN m.hasAttendedExam = false THEN 1 END),
            COUNT(CASE WHEN m.subjectStatus = 'ELIGIBLE' THEN 1 END),
            COUNT(CASE WHEN m.subjectStatus = 'INELIGIBLE' THEN 1 END)
        FROM Mark m 
        JOIN m.student s 
        JOIN s.personalAcademicInfo p 
        WHERE 1=1
        """);

        Map<String, Object> parameters = new HashMap<>();

        if (faculty != null) {
            jpql.append(" AND p.faculty = :faculty");
            parameters.put("faculty", faculty); // Pass enum directly for JPQL
        }
        if (department != null) {
            jpql.append(" AND p.department = :department");
            parameters.put("department", department);
        }
        if (specialty != null) {
            jpql.append(" AND p.specialty = :specialty");
            parameters.put("specialty", specialty);
        }

        jpql.append(" GROUP BY m.subject");

        TypedQuery<Object[]> query = getEntityManager().createQuery(jpql.toString(), Object[].class);
        parameters.forEach(query::setParameter);

        List<Object[]> results = query.getResultList();

        return results.stream().map(r -> SubjectStatsDTO.builder()
                        .subject(r[0].toString())
                        .averageGrade(r[1] != null ? ((Number) r[1]).doubleValue() : 0.0)
                        .sixes(r[2] != null ? ((Number) r[2]).longValue() : 0L)
                        .fives(r[3] != null ? ((Number) r[3]).longValue() : 0L)
                        .fours(r[4] != null ? ((Number) r[4]).longValue() : 0L)
                        .threes(r[5] != null ? ((Number) r[5]).longValue() : 0L)
                        .twos(r[6] != null ? ((Number) r[6]).longValue() : 0L)
                        .notAttended(r[7] != null ? ((Number) r[7]).longValue() : 0L)
                        .eligible(r[8] != null ? ((Number) r[8]).longValue() : 0L)
                        .ineligible(r[9] != null ? ((Number) r[9]).longValue() : 0L)
                        .build())
                .toList();
    }

    public List<StudentAverageDTO> getStudentAverages(Faculty faculty, String department, String specialty) {
        List<Object[]> results = getEntityManager().createQuery("""
            select s.id, p.firstName, p.lastName, p.facultyNumber,
                   avg(cast(m.mark as int))
            from Student s
            join s.personalAcademicInfo p
            join s.marks m
            where (:faculty is null or p.faculty = :faculty)
              and (:department is null or p.department = :department)
              and (:specialty is null or p.specialty = :specialty)
              and m.hasAttendedExam = true
              and m.subjectStatus = 'ELIGIBLE'
              and m.mark in ('2','3','4','5','6')
            group by s.id, p.firstName, p.lastName, p.facultyNumber
            """, Object[].class)
                .setParameter("faculty", faculty)
                .setParameter("department", department)
                .setParameter("specialty", specialty)
                .getResultList();

        return results.stream()
                .map(r -> StudentAverageDTO.builder()
                        .studentId((Long) r[0])
                        .studentName(r[1].toString()+" "+r[2].toString()+" ")
                        .studentFacultyNumber(r[3].toString())
                        .averageGrade(((Number) r[4]).doubleValue())
                        .build())
                .toList();
    }

    public List<SpecialtyAverageDTO> getSpecialtyAverages(String specialty) {
        List<Object[]> results = getEntityManager().createNativeQuery("""
            select case when grouping(p.courseyear) = 1 then 'ВСИЧКИ'
                        else p.courseyear end as courseYear,
                   avg(cast(m.mark as int)) as averageGrade
            from student s
            join personalacademicinfo p on s.personal_info_id = p.id
            join mark m on m.student_id = s.id
            where p.specialty = ?1
              and m.hasAttendedExam = true
              and m.subjectStatus = 'ELIGIBLE'
              and m.mark in ('2','3','4','5','6')
            group by rollup(p.courseyear)
            """)
//                .setParameter(1, faculty.name())
//                .setParameter(2, department)
                .setParameter(1, specialty)
                .getResultList();

        return results.stream()
                .map(r -> SpecialtyAverageDTO.builder()
                        .courseYear(r[0].toString()) // "1", "2", ..., "ALL"
                        .averageGrade(r[1] != null ? ((Number) r[1]).doubleValue() : 0.0)
                        .build())
                .toList();
    }


}
