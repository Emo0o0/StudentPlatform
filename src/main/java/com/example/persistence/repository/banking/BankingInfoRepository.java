package com.example.persistence.repository.banking;

import com.example.persistence.entity.scholarship.banking.BankingInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BankingInfoRepository implements PanacheRepository<BankingInfo> {
}
