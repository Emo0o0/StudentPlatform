package bg.tu_varna.sit.api.inputoutput.student.getscholarshipforms;

import bg.tu_varna.sit.api.inputoutput.scholarship.meritincome.ChildDTO;
import bg.tu_varna.sit.api.inputoutput.scholarship.meritincome.SiblingDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeritWithIncomeScholarshipDTO {

    private String familyStatus;

    //married
    private String spouseName;
    private String spouseEmploymentStatus;
    private List<ChildDTO> children;

    //single
    private String fatherName;
    private String fatherStatus;
    private String motherName;
    private String motherStatus;
    private List<SiblingDTO> siblings = new ArrayList<>();

    //Income
    private String salaries;
    private String pensions;
    private String unemploymentBenefits;
    private String socialAid;
    private String familyAllowances;
    private String childCareAllowances;
    private String personalScholarships;
    private String rent;
    private String honorariums;
    private String alimony;
    private String businessIncome;
    private String otherIncome;
    private String totalIncome;
    private String monthlyIncomePerMember;
}
