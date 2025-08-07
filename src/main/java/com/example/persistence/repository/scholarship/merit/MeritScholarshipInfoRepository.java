package com.example.persistence.repository.scholarship.merit;

import com.example.persistence.entity.scholarship.merit.MeritScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeritScholarshipInfoRepository implements PanacheRepository<MeritScholarshipInfo> {
}
