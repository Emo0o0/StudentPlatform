package com.example.persistence.entity.scholarship.banking;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class BankingInfo {
    @Enumerated(EnumType.STRING)
    private BankName bankName;

    private String bankAccount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private List<BankDocument> bankDocuments = new ArrayList<>();
}
