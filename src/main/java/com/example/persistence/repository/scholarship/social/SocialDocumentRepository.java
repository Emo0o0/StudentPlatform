package com.example.persistence.repository.scholarship.social;

import com.example.persistence.entity.scholarship.social.SocialDocument;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SocialDocumentRepository implements PanacheRepository<SocialDocument> {
}
