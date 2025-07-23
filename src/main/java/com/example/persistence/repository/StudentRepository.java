package com.example.persistence.repository;

import com.example.persistence.entity.Student;
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

}
