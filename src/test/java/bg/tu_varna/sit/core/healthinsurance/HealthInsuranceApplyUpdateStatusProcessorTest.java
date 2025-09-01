package bg.tu_varna.sit.core.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateapply.HealthInsuranceApplyFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateapply.HealthInsuranceApplyFormUpdateStatusResponse;
import bg.tu_varna.sit.core.exception.InvalidEnumValueException;
import bg.tu_varna.sit.core.exception.healthinsurance.HealthInsuranceApplyFormIdNotFoundException;
import bg.tu_varna.sit.core.service.healthinsurance.HealthInsuranceApplyFormUpdateStatusProcessor;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceApplyForm;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceApplyFormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HealthInsuranceApplyUpdateStatusProcessorTest {

    @Mock
    HealthInsuranceApplyFormRepository healthInsuranceApplyFormRepository;
    @InjectMocks
    HealthInsuranceApplyFormUpdateStatusProcessor healthInsuranceApplyUpdateStatusProcessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        HealthInsuranceApplyForm mockForm = HealthInsuranceApplyForm.builder()
                .formId(1L)
                .build();
        when(healthInsuranceApplyFormRepository.findByIdOptional(1L)).thenReturn(java.util.Optional.of(mockForm));
        when(healthInsuranceApplyFormRepository.findById(1L)).thenReturn(mockForm);
    }

    @Test
    void testProcess() {
        HealthInsuranceApplyFormUpdateStatusResponse response = healthInsuranceApplyUpdateStatusProcessor.process(
                HealthInsuranceApplyFormUpdateStatusRequest.builder()
                        .formId("1")
                        .formStatus("APPROVED")
                        .build());
        assertTrue(response.isSuccess());
        verify(healthInsuranceApplyFormRepository, times(1)).findByIdOptional(1L);

        HealthInsuranceApplyForm updatedForm = healthInsuranceApplyFormRepository.findById(1L);
        assertEquals(FormStatus.APPROVED, updatedForm.getFormStatus());
    }

    @Test
    void testProcessInvalidForm() {
        when(healthInsuranceApplyFormRepository.findByIdOptional(999L)).thenReturn(Optional.empty());

        HealthInsuranceApplyFormIdNotFoundException exception = assertThrows(
                HealthInsuranceApplyFormIdNotFoundException.class,
                () -> healthInsuranceApplyUpdateStatusProcessor.process(
                        HealthInsuranceApplyFormUpdateStatusRequest.builder()
                                .formId("999")
                                .formStatus("APPROVED")
                                .build())
        );

        String expectedMessage = "Health insurance apply form with id 999 not found!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testProcessInvalidEnumValue(){
        assertThrows(InvalidEnumValueException.class, () -> {
            healthInsuranceApplyUpdateStatusProcessor.process(
                    HealthInsuranceApplyFormUpdateStatusRequest.builder()
                            .formId("1")
                            .formStatus("INVALID_STATUS")
                            .build());
        });
    }

}
