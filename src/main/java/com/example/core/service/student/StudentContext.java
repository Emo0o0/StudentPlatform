package com.example.core.service.student;

import com.example.core.exception.student.StudentNotFoundException;
import com.example.persistence.entity.Student;
import com.example.persistence.repository.StudentRepository;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class StudentContext {

    @Inject
    StudentRepository studentRepository;
    @Inject
    SecurityIdentity securityIdentity;

    public Student getCurrentStudent() {
        String facultyNumber = securityIdentity.getPrincipal().getName().substring(1, 9);
        return studentRepository.findByFacultyNumber(facultyNumber)
                .orElseThrow(() -> new StudentNotFoundException("Student with faculty number [" + facultyNumber + "] was not found"));
    }

}
