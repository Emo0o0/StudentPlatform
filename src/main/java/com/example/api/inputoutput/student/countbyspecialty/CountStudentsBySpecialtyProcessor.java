package com.example.api.inputoutput.student.countbyspecialty;

import com.example.persistence.entity.PersonalAcademicInfo;
import com.example.persistence.entity.StudentStatus;
import com.example.persistence.entity.enums.CourseYear;
import com.example.persistence.entity.enums.DegreeLevel;
import com.example.persistence.entity.enums.Faculty;
import com.example.persistence.entity.enums.Semester;
import com.example.persistence.repository.PersonalAcademicInfoRepository;
import com.example.persistence.repository.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CountStudentsBySpecialtyProcessor implements CountStudentsBySpecialtyOperation {

    @Inject
    StudentRepository studentRepository;

    @Override
    @Transactional
    public CountStudentsBySpecialtyResponse process(CountStudentsBySpecialtyRequest request) {

        long students = studentRepository.countBySpecialty(request.getSpecialty());

        return CountStudentsBySpecialtyResponse.builder()
                .count(students)
                .build();
    }
}
