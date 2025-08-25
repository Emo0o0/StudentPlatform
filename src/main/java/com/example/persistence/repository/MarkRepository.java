package com.example.persistence.repository;

import com.example.persistence.entity.Mark;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public class MarkRepository implements PanacheRepository<Mark> {

    // Среден успех по дисциплина (без неявили и без незаверени)
    public Double avgGradeBySubject(String subject) {
        List<Integer> grades = find("subject = ?1 and hasAttendedExam = true and subjectStatus = 'ELIGIBLE' and mark in ('2','3','4','5','6')", subject)
                .stream()
                .map(m -> Integer.parseInt(((Mark) m).getMark()))
                .toList();

        if (grades.isEmpty()) return null;

        return grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
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
}
