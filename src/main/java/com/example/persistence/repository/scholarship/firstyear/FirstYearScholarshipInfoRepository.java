package com.example.persistence.repository.scholarship.firstyear;

import com.example.persistence.entity.scholarship.firstyear.FirstYearScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FirstYearScholarshipInfoRepository implements PanacheRepository<FirstYearScholarshipInfo> {
}
