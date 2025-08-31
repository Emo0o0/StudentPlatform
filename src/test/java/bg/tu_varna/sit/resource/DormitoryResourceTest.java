package bg.tu_varna.sit.resource;

import bg.tu_varna.sit.api.PersonalAcademicInfoDTO;
import bg.tu_varna.sit.api.inputoutput.dormitory.apply.DormitoryApplyRequest;
import bg.tu_varna.sit.api.inputoutput.dormitory.apply.FamilyMemberDto;
import bg.tu_varna.sit.persistence.entity.enums.DegreeLevel;
import bg.tu_varna.sit.persistence.entity.enums.FamilyMemberRelation;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class DormitoryResourceTest {
    @Test
    void testApplyForDormitory() {

        List<FamilyMemberDto> familyMembers = new ArrayList<>();

        FamilyMemberDto familyMember = FamilyMemberDto.builder()
                .name("Emo Kolev")
                .address("Varna")
                .phoneNumber("0888123456")
                .dateOfBirth("2000-01-01")
                .familyMemberRelation(FamilyMemberRelation.BROTHER)
                .build();
        familyMembers.add(familyMember);

        DormitoryApplyRequest request = DormitoryApplyRequest.builder()
                .personalAcademicInfo(PersonalAcademicInfoDTO.builder()
                        .email("E@E")
                        .firstName("Emo")
                        .lastName("Kolev")
                        .egn("1234567890")
                        .address("Varna")
                        .phoneNumber("0888123456")
                        .placeOfResidence("Varna")
                        .streetName("SomeStreet")
                        .streetNumber(12)
                        .entrance("A")
                        .floor(1)
                        .flatNumber(1)
                        .facultyNumber("12345678")
                        .courseYear("II")
                        .semester("4")
                        .degreeLevel("BACHELOR")
                        .faculty("FMI")
                        .specialty("KSI")
                        .studentGroup(1)
                        .subGroup('A')
                        .build())
                .degreeLevel(DegreeLevel.BACHELOR.name())
                .familyMembers(familyMembers)
                .buildingNumber(1)
                .roomNumber(101)
                .keepRoomFormId(null)
                .build();

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/form/dormitory/apply")
                .then()
                .statusCode(201)
                .body("success", is(true));
    }
}
