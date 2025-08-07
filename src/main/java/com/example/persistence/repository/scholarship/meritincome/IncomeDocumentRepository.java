package com.example.persistence.repository.scholarship.meritincome;

import com.example.persistence.entity.scholarship.meritincome.IncomeDocument;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IncomeDocumentRepository implements PanacheRepository<IncomeDocument> {
}
