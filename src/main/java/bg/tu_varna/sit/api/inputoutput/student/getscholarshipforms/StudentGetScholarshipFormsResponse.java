package bg.tu_varna.sit.api.inputoutput.student.getscholarshipforms;

import bg.tu_varna.sit.api.inputoutput.scholarship.BankingInfoDTO;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetScholarshipFormsResponse {

    private String formId;
    private String studentFirstName;
    private String studentLastName;
    private String studentFacultyNumber;

    private String previousGPA;
    private String scholarshipType;

    //merit with income
    private MeritWithIncomeScholarshipDTO meritWithIncomeScholarship;

    //social
    private SocialScholarshipDTO socialScholarship;

    //first year
    private FirstYearScholarshipDTO firstYearScholarship;

    //special achievement
    private SpecialAchievementScholarshipDTO specialAchievementScholarship;

    //banking info
    private BankingInfoDTO bankingInfo;
    private String formStatus;

}
