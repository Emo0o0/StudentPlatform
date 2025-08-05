package com.example.api.inputoutput.dormitory.apply;

import com.example.persistence.entity.enums.FamilyMemberRelation;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberDto {

    private String name;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;
    private FamilyMemberRelation familyMemberRelation;
}
