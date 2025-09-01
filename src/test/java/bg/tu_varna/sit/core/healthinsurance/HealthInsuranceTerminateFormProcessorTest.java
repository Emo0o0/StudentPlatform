package bg.tu_varna.sit.core.healthinsurance;

import bg.tu_varna.sit.api.PersonalAcademicInfoDTO;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.late.HealthInsuranceLateResponse;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.terminate.HealthInsuranceTerminateResponse;
import bg.tu_varna.sit.core.service.healthinsurance.HealthInsuranceLateOperationProcessor;
import bg.tu_varna.sit.core.service.healthinsurance.HealthInsuranceTerminateOperationProcessor;
import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceLateForm;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceTerminateForm;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceLateFormRepository;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceTerminateFormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HealthInsuranceTerminateFormProcessorTest {
    @Mock
    HealthInsuranceTerminateFormRepository healthInsuranceTerminateFormRepository;

    @Mock
    StudentContext studentContext;

    @InjectMocks
    HealthInsuranceTerminateOperationProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess() {
        when(studentContext.getCurrentStudent()).thenReturn(null);

        HealthInsuranceTerminateRequest request = HealthInsuranceTerminateRequest.builder()
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
                .terminationReason("Work")
                .schoolYear("2023/2024")
                .build();

        HealthInsuranceTerminateResponse response = processor.process(request);

        assertTrue(response.getSuccess());
        verify(healthInsuranceTerminateFormRepository, times(1)).persist(any(HealthInsuranceTerminateForm.class));
    }
}
