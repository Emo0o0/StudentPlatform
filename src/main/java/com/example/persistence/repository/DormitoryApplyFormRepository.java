package com.example.persistence.repository;

import com.example.persistence.entity.DormitoryApplyForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DormitoryApplyFormRepository implements PanacheRepository<DormitoryApplyForm> {
}
