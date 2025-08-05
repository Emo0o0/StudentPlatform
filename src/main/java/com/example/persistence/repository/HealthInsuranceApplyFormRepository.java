package com.example.persistence.repository;

import com.example.persistence.entity.insurance.HealthInsuranceApplyForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HealthInsuranceApplyFormRepository implements PanacheRepository<HealthInsuranceApplyForm> {
}
