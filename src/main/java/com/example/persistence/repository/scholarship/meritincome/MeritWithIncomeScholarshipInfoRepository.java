package com.example.persistence.repository.scholarship.meritincome;

import com.example.persistence.entity.scholarship.meritincome.MeritWithIncomeScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeritWithIncomeScholarshipInfoRepository implements PanacheRepository<MeritWithIncomeScholarshipInfo> {
}
