package com.example.persistence.repository.scholarship.foreign;

import com.example.persistence.entity.scholarship.foreign.ForeignStudentScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ForeignStudentScholarshipInfoRepository implements PanacheRepository<ForeignStudentScholarshipInfo> {
}
