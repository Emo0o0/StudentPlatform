package com.example.api.inputoutput.student.countbystatus;

import com.example.persistence.entity.StudentStatus;
import com.example.persistence.entity.enums.Faculty;
import com.example.persistence.repository.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CountStudentsByStatusProcessor implements CountStudentsByStatusOperation {

    @Inject
    StudentRepository studentRepository;

    @Override
    public CountStudentsByStatusResponse process(CountStudentsByStatusRequest request) {

        long students = studentRepository.countByStatus(StudentStatus.valueOf(request.getStudentStatus()),
                Faculty.valueOf(request.getFaculty()),
                request.getDepartment(),
                request.getSpecialty());

        return CountStudentsByStatusResponse.builder()
                .count(students)
                .build();
    }
}
