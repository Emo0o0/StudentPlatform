package com.example.persistence.repository.scholarship.social;

import com.example.persistence.entity.scholarship.social.SocialScholarshipInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SocialScholarshipInfoRepository implements PanacheRepository<SocialScholarshipInfo> {
}
