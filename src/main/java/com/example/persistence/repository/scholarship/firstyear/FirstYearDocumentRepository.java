package com.example.persistence.repository.scholarship.firstyear;

import com.example.persistence.entity.scholarship.firstyear.FirstYearDocument;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FirstYearDocumentRepository implements PanacheRepository<FirstYearDocument> {
}
