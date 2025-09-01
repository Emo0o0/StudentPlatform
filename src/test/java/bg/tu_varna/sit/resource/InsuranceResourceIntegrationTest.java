package bg.tu_varna.sit.resource;

import bg.tu_varna.sit.api.PersonalAcademicInfoDTO;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateRequest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class InsuranceResourceIntegrationTest {

    private PersonalAcademicInfoDTO buildInfo(){
        return PersonalAcademicInfoDTO.builder()
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
                .courseYear("I")
                .semester("4")
                .degreeLevel("BACHELOR")
                .faculty("COMPUTER_AUTOMATION")
                .specialty("KSI")
                .studentGroup(1)
                .subGroup('A')
                .build();
    }

    @Test
    void testApplyForHealthInsurance() {
        HealthInsuranceApplyRequest request = HealthInsuranceApplyRequest.builder()
                .personalAcademicInfo(buildInfo())
                .currentInsurer("Some Insurer")
                .isReceivingPension(false)
                .isReceivingOtherInsuredIncome(false)
                .isReceivingWorkRelatedIncome(false)
                .build();

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/form/healthInsurance/apply")
                .then()
                .statusCode(201)
                .body("success", is(true));
    }

    @Test
    void testApplyForLateHealthInsurance() {
        HealthInsuranceLateRequest request = HealthInsuranceLateRequest.builder()
                .personalAcademicInfo(buildInfo())
                .schoolYear("2023/2024")
                .build();

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/form/healthInsurance/late")
                .then()
                .statusCode(201)
                .body("success", is(true));
    }

    @Test
    void testTerminateHealthInsurance() {
        HealthInsuranceTerminateRequest request = HealthInsuranceTerminateRequest.builder()
                .terminationReason("Graduated")
                .terminationReason("Graduated")
                .personalAcademicInfo(buildInfo())
                .schoolYear("2023/2024")
                .build();

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/form/healthInsurance/terminate")
                .then()
                .statusCode(201)
                .body("success", is(true));
    }

    @Test
    void testGetInsuranceApplyForms() {
        given()
//                .queryParam("studentId", "123")
                .queryParam("specialty", "СИТ")
                .when()
                .get("/form/healthInsurance/apply")
                .then()
                .statusCode(200)
                .body("forms", not(empty()));
    }

    @Test
    void testGetInsuranceLateForms() {
        given()
//                .queryParam("studentId", "123")
                .queryParam("specialty", "СИТ")
                .when()
                .get("/form/healthInsurance/late")
                .then()
                .statusCode(200)
                .body("forms", not(empty()));
    }

    @Test
    void testGetInsuranceTerminateForms() {
        given()
//                .queryParam("studentId", "123")
                .queryParam("specialty", "СИТ")
                .when()
                .get("/form/healthInsurance/terminate")
                .then()
                .statusCode(200)
                .body("forms", not(empty()));
    }

    @Test
    void testUpdateApplyFormStatus() {
        given()
                .queryParam("formId", "1")
                .queryParam("status", "APPROVED")
                .when()
                .put("/form/healthInsurance/apply/update")
                .then()
                .statusCode(200)
                .body("success", is(true));
    }

    @Test
    void testUpdateLateFormStatus() {
        given()
                .queryParam("formId", "1")
                .queryParam("status", "APPROVED")
                .when()
                .put("/form/healthInsurance/late/update")
                .then()
                .statusCode(200)
                .body("success", is(true));
    }

    @Test
    void testUpdateTerminateFormStatus() {
        given()
                .queryParam("formId", "1")
                .queryParam("status", "APPROVED")
                .when()
                .put("/form/healthInsurance/terminate/update")
                .then()
                .statusCode(200)
                .body("success", is(true));
    }

}
