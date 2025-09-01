package bg.tu_varna.sit.core.healthinsurance;

import bg.tu_varna.sit.api.PersonalAcademicInfoDTO;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.apply.HealthInsuranceApplyResponse;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateResponse;
import bg.tu_varna.sit.core.service.healthinsurance.HealthInsuranceApplyOperationProcessor;
import bg.tu_varna.sit.core.service.healthinsurance.HealthInsuranceLateOperationProcessor;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceApplyForm;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceLateForm;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceApplyFormRepository;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceLateFormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HealthInsuranceLateFormProcessorTest {
    @Mock
    HealthInsuranceLateFormRepository healthInsuranceLateFormRepository;

    @Mock
    StudentContext studentContext;

    @InjectMocks
    HealthInsuranceLateOperationProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess() {
        when(studentContext.getCurrentStudent()).thenReturn(null);

        HealthInsuranceLateRequest request = HealthInsuranceLateRequest.builder()
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
                        .facultyNumber("123456")
                        .courseYear("II")
                        .semester("4")
                        .degreeLevel("BACHELOR")
                        .faculty("MECHANICAL_TECHNOLOGICAL")
                        .specialty("KSI")
                        .studentGroup(1)
                        .subGroup('A')
                        .build())
                .schoolYear("2023/2024")
                .build();

        HealthInsuranceLateResponse response = processor.process(request);

        assertTrue(response.getSuccess());
        verify(healthInsuranceLateFormRepository, times(1)).persist(any(HealthInsuranceLateForm.class));
    }
}
