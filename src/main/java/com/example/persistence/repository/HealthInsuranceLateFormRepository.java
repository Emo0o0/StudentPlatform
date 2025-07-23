package com.example.persistence.repository;

import com.example.persistence.entity.HealthInsuranceLateForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HealthInsuranceLateFormRepository implements PanacheRepository<HealthInsuranceLateForm> {
}
