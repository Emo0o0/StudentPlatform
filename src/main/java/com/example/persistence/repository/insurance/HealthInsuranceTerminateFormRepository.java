package com.example.persistence.repository.insurance;

import com.example.persistence.entity.insurance.HealthInsuranceTerminateForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HealthInsuranceTerminateFormRepository implements PanacheRepository<HealthInsuranceTerminateForm> {
}
