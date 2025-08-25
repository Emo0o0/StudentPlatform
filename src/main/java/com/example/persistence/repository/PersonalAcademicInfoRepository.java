package com.example.persistence.repository;

import com.example.persistence.entity.PersonalAcademicInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonalAcademicInfoRepository implements PanacheRepository<PersonalAcademicInfo> {
}
