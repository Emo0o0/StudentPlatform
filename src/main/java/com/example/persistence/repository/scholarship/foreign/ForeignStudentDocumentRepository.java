package com.example.persistence.repository.scholarship.foreign;

import com.example.persistence.entity.scholarship.foreign.ForeignStudentDocument;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ForeignStudentDocumentRepository implements PanacheRepository<ForeignStudentDocument> {
}
