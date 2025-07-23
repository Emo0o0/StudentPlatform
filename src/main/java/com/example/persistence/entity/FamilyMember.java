package com.example.persistence.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class FamilyMember {

    private String name;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;
    @Enumerated(EnumType.STRING)
    private FamilyMemberRelation familyMemberRelation;
}
