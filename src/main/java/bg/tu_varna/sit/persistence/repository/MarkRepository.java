package bg.tu_varna.sit.persistence.repository;

import bg.tu_varna.sit.api.inputoutput.student.SpecialtyAverageDTO;
import bg.tu_varna.sit.api.inputoutput.student.StudentAverageDTO;
import bg.tu_varna.sit.api.inputoutput.student.SubjectStatsDTO;
import bg.tu_varna.sit.persistence.entity.Mark;
import bg.tu_varna.sit.persistence.entity.enums.Faculty;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

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

    // Брой не се явили
    public long countNotAttended(String subject) {
        return count("subject = ?1 and hasAttendedExam = false", subject);
    }

    // Брой заверени
    public long countEligible(String subject) {
        return count("subject = ?1 and subjectStatus = 'ELIGIBLE'", subject);
    }

    // Брой незаверени
    public long countIneligible(String subject) {
        return count("subject = ?1 and subjectStatus = 'INELIGIBLE'", subject);
    }

    // Брой "зачита се"
    public long countRecognized(String subject) {
        return count("subject = ?1 and mark = 'зачита се'", subject);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public List<SubjectStatsDTO> getSubjectStatisticsBySpecialty(Faculty faculty, String department, String specialty) {
        List<Object[]> results = getEntityManager().createNativeQuery("""
            select 
                m.subject,

                case 
                    -- If there are numeric marks, calculate their average
                    when count(case when m.hasAttendedExam = true 
                                      and m.subjectStatus = 'ELIGIBLE' 
                                      and m.mark in ('2','3','4','5','6') 
                                then 1 end) > 0
                    then avg(case 
                                when m.hasAttendedExam = true 
                                 and m.subjectStatus = 'ELIGIBLE' 
                                 and m.mark in ('2','3','4','5','6') 
                             then cast(m.mark as int) 
                             end)

                    -- Else: return % of "Зачита се"
                    else 100.0 * count(case 
                                         when m.hasAttendedExam = true 
                                          and m.subjectStatus = 'ELIGIBLE' 
                                          and m.mark = 'Зачита се' 
                                        then 1 end)
                           / nullif(count(case 
                                             when m.mark in ('Зачита се','Не се зачита') 
                                          then 1 end),0)
                end as averageGradeOrPercent,

                count(case when m.mark = '6' then 1 end) as sixes,
                count(case when m.mark = '5' then 1 end) as fives,
                count(case when m.mark = '4' then 1 end) as fours,
                count(case when m.mark = '3' then 1 end) as threes,
                count(case when m.mark = '2' then 1 end) as twos,
                count(case when m.hasAttendedExam = false then 1 end) as notAttended,
                count(case when m.subjectStatus = 'ELIGIBLE' then 1 end) as eligible,
                count(case when m.subjectStatus = 'INELIGIBLE' then 1 end) as ineligible

            from mark m
            join student s on m.student_id = s.id
            join personalacademicinfo p on s.personal_info_id = p.id
            where p.faculty = ?1 and p.department = ?2 and p.specialty = ?3
            group by m.subject
            """)
                .setParameter(1, faculty.name())
                .setParameter(2, department)
                .setParameter(3, specialty)
                .getResultList();

        return results.stream().map(r -> SubjectStatsDTO.builder()
                        .subject(r[0].toString())
                        .averageGrade(r[1] != null ? ((Number) r[1]).doubleValue() : 0.0) // either avg grade or % "Зачита се"
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
            select s.id,
                   avg(cast(m.mark as int))
            from Student s
            join s.personalAcademicInfo p
            join s.marks m
            where p.faculty = :faculty
              and p.department = :department
              and p.specialty = :specialty
              and m.hasAttendedExam = true
              and m.subjectStatus = 'ELIGIBLE'
              and m.mark in ('2','3','4','5','6')
            group by s.id
            """, Object[].class)
                .setParameter("faculty", faculty)
                .setParameter("department", department)
                .setParameter("specialty", specialty)
                .getResultList();

        return results.stream()
                .map(r -> StudentAverageDTO.builder()
                        .studentId((Long) r[0])
                        .averageGrade(((Number) r[1]).doubleValue())
                        .build())
                .toList();
    }

    public List<SpecialtyAverageDTO> getSpecialtyAverages(Faculty faculty, String department, String specialty) {
        List<Object[]> results = getEntityManager().createNativeQuery("""
            select case when grouping(p.courseyear) = 1 then 'ALL'
                        else p.courseyear end as courseYear,
                   avg(cast(m.mark as int)) as averageGrade
            from student s
            join personalacademicinfo p on s.personal_info_id = p.id
            join mark m on m.student_id = s.id
            where p.faculty = ?1
              and p.department = ?2
              and p.specialty = ?3
              and m.hasAttendedExam = true
              and m.subjectStatus = 'ELIGIBLE'
              and m.mark in ('2','3','4','5','6')
            group by rollup(p.courseyear)
            """)
                .setParameter(1, faculty.name())
                .setParameter(2, department)
                .setParameter(3, specialty)
                .getResultList();

        return results.stream()
                .map(r -> SpecialtyAverageDTO.builder()
                        .courseYear(r[0].toString()) // "1", "2", ..., "ALL"
                        .averageGrade(r[1] != null ? ((Number) r[1]).doubleValue() : 0.0)
                        .build())
                .toList();
    }


}
