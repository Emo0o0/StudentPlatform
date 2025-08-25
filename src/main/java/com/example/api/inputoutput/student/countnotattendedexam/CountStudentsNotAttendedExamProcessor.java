package com.example.api.inputoutput.student.countnotattendedexam;

import com.example.persistence.entity.enums.Faculty;
import com.example.persistence.repository.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CountStudentsNotAttendedExamProcessor implements CountStudentsNotAttendedExamOperation {

    @Inject
    StudentRepository studentRepository;

    @Override
    public CountStudentsNotAttendedExamResponse process(CountStudentsNotAttendedExamRequest request) {

        long students = studentRepository.countNotAttendedExam(
                Faculty.valueOf(request.getFaculty()),
                request.getDepartment(),
                request.getSpecialty());

        return CountStudentsNotAttendedExamResponse.builder()
                .count(students)
                .build();
    }
}
