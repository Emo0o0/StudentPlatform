package bg.tu_varna.sit.core.healthinsurance;

import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate.HealthInsuranceTerminateFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.healthinsurance.updateterminate.HealthInsuranceTerminateFormUpdateStatusResponse;
import bg.tu_varna.sit.core.exception.InvalidEnumValueException;
import bg.tu_varna.sit.core.exception.healthinsurance.HealthInsuranceTerminateFormIdNotFoundException;
import bg.tu_varna.sit.core.service.healthinsurance.HealthInsuranceTerminateFormUpdateStatusProcessor;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceTerminateForm;
import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceTerminateFormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HealthInsuranceTerminateUpdateStatusProcessorTest {

    @Mock
    HealthInsuranceTerminateFormRepository healthInsuranceTerminateFormRepository;
    @InjectMocks
    HealthInsuranceTerminateFormUpdateStatusProcessor healthInsuranceTerminateFormUpdateStatusProcessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        HealthInsuranceTerminateForm mockForm = HealthInsuranceTerminateForm.builder()
                .formStatus(FormStatus.SENT)
                .formId(1L)
                .build();
        when(healthInsuranceTerminateFormRepository.findByIdOptional(1L)).thenReturn(Optional.of(mockForm));
        when(healthInsuranceTerminateFormRepository.findById(1L)).thenReturn(mockForm);
    }

    @Test
    void testProcess() {
        HealthInsuranceTerminateFormUpdateStatusResponse response = healthInsuranceTerminateFormUpdateStatusProcessor.process(
                HealthInsuranceTerminateFormUpdateStatusRequest.builder()
                        .formId("1")
                        .formStatus("APPROVED")
                        .build());
        assertTrue(response.isSuccess());
        verify(healthInsuranceTerminateFormRepository, times(1)).findByIdOptional(1L);

        HealthInsuranceTerminateForm updatedForm = healthInsuranceTerminateFormRepository.findById(1L);
        assertEquals(FormStatus.APPROVED, updatedForm.getFormStatus());
    }

    @Test
    void testProcessInvalidForm() {
        when(healthInsuranceTerminateFormRepository.findByIdOptional(999L)).thenReturn(Optional.empty());

        HealthInsuranceTerminateFormIdNotFoundException exception = assertThrows(
                HealthInsuranceTerminateFormIdNotFoundException.class,
                () -> healthInsuranceTerminateFormUpdateStatusProcessor.process(
                        HealthInsuranceTerminateFormUpdateStatusRequest.builder()
                                .formId("999")
                                .formStatus("APPROVED")
                                .build())
        );
        assertEquals("Health insurance terminate form with id 999 not found!", exception.getMessage());
    }

    @Test
    void testProcessInvalidStatus() {
        assertThrows(
                InvalidEnumValueException.class,
                () -> healthInsuranceTerminateFormUpdateStatusProcessor.process(
                        HealthInsuranceTerminateFormUpdateStatusRequest.builder()
                                .formId("1")
                                .formStatus("INVALID_STATUS")
                                .build())
        );
    }
}
