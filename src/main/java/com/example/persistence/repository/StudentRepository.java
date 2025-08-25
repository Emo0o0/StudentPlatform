package com.example.persistence.repository;

import com.example.persistence.entity.Student;
import com.example.persistence.entity.StudentStatus;
import com.example.persistence.entity.enums.Faculty;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    public Student findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Optional<Student> findByFacultyNumber(String facultyNumber) {
        return find("facultyNumber", facultyNumber).firstResultOptional();
    }


    // Брой студенти по специалност
    public long countBySpecialty(String specialty) {
        return find("personalAcademicInfo.specialty = ?1", specialty).count();
    }

    // Брой студенти по статус (напр. прекъснал, възстановен, дипломиран)
    public long countByStatus(StudentStatus status, Faculty faculty, String department, String specialty) {
        return find("personalAcademicInfo.studentStatus = ?1 and personalAcademicInfo.faculty = ?2 and personalAcademicInfo.department = ?3 and personalAcademicInfo.specialty = ?4",
                status, faculty, department, specialty).count();
    }

    // Брой студенти не се явили на изпит (поне в една дисциплина)
    public long countNotAttendedExam(Faculty faculty, String department, String specialty) {
        return find("select distinct s from Student s join s.marks m " +
                        "where m.hasAttendedExam = false and " +
                        "s.personalAcademicInfo.faculty = ?1 and s.personalAcademicInfo.department = ?2 and s.personalAcademicInfo.specialty = ?3",
                faculty, department, specialty).count();
    }

    // Брой студенти поне с една двойка
    public long countWithFail(Faculty faculty, String department, String specialty) {
        return find("select distinct s from Student s join s.marks m " +
                        "where m.mark = '2' and " +
                        "s.personalAcademicInfo.faculty = ?1 and s.personalAcademicInfo.department = ?2 and s.personalAcademicInfo.specialty = ?3",
                faculty, department, specialty).count();
    }

}
