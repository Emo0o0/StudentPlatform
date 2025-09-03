//package bg.tu_varna.sit.resource;
//
//import bg.tu_varna.sit.api.PersonalAcademicInfoDTO;
//import bg.tu_varna.sit.api.inputoutput.dormitory.apply.DormitoryApplyRequest;
//import bg.tu_varna.sit.api.inputoutput.dormitory.apply.FamilyMemberDto;
//import bg.tu_varna.sit.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomRequest;
//import bg.tu_varna.sit.persistence.entity.enums.DegreeLevel;
//import bg.tu_varna.sit.persistence.entity.enums.FamilyMemberRelation;
//import io.quarkus.test.junit.QuarkusTest;
//import io.quarkus.test.security.TestSecurity;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//
//@QuarkusTest
//public class DormitoryResourceIntegrationTest {
//
//
//    @Test
//    @TestSecurity
//    void testApplyForDormitory() {
//        FamilyMemberDto familyMember = FamilyMemberDto.builder()
//                .name("John Doe")
//                .address("City")
//                .phoneNumber("1234567890")
//                .dateOfBirth("2000-01-01")
//                .familyMemberRelation(FamilyMemberRelation.BROTHER)
//                .build();
//
//        DormitoryApplyRequest request = DormitoryApplyRequest.builder()
//                .personalAcademicInfo(PersonalAcademicInfoDTO.builder()
//                        .email("test@example.com")
//                        .firstName("John")
//                        .lastName("Doe")
//                        .egn("1234567890")
//                        .address("City")
//                        .phoneNumber("1234567890")
//                        .placeOfResidence("City")
//                        .streetName("Main Street")
//                        .streetNumber(1)
//                        .entrance("A")
//                        .floor(1)
//                        .flatNumber(1)
//                        .facultyNumber("123456")
//                        .courseYear("I")
//                        .semester("1")
//                        .degreeLevel("BACHELOR")
//                        .faculty("FMI")
//                        .specialty("CS")
//                        .studentGroup(1)
//                        .subGroup('A')
//                        .build())
//                .degreeLevel(DegreeLevel.BACHELOR.name())
//                .familyMembers(List.of(familyMember))
//                .buildingNumber(1)
//                .roomNumber(101)
//                .keepRoomFormId(null)
//                .build();
//
//        given()
//                .contentType("application/json")
//                .body(request)
//                .when()
//                .post("/form/dormitory/apply")
//                .then()
//                .statusCode(201)
//                .body("success", is(true));
//    }
//
//    @Test
//    void testRequestToKeepRoom() {
//        DormitoryKeepRoomRequest request = DormitoryKeepRoomRequest.builder()
//                .buildingNumber(1)
//                .roomNumber(101)
//                .build();
//
//        given()
//                .contentType("application/json")
//                .body(request)
//                .when()
//                .post("/form/dormitory/keepRoom")
//                .then()
//                .statusCode(201)
//                .body("success", is(true));
//    }
//
//    @Test
//    void testGetDormitoryApplyForms() {
//        given()
////                .queryParam("studentId", "123")
//                .queryParam("specialty", "СИТ")
//                .when()
//                .get("/form/dormitory/apply")
//                .then()
//                .statusCode(200)
//                .body("forms", not(empty()));
//    }
//
//    @Test
//    void testUpdateFormStatus() {
//        given()
//                .queryParam("formId", "1")
//                .queryParam("status", "APPROVED")
//                .when()
//                .put("/form/dormitory/update")
//                .then()
//                .statusCode(200)
//                .body("success", is(true));
//    }
//
//}
