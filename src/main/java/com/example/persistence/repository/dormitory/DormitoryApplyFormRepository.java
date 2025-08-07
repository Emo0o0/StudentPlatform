package com.example.persistence.repository.dormitory;

import com.example.persistence.entity.dormitory.DormitoryApplyForm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DormitoryApplyFormRepository implements PanacheRepository<DormitoryApplyForm> {
}
