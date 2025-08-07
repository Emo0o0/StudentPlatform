package com.example.persistence.repository.scholarship;

import com.example.persistence.entity.scholarship.ScholarshipApplyForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScholarshipApplyFormRepository implements PanacheRepository<ScholarshipApplyForm> {
}
