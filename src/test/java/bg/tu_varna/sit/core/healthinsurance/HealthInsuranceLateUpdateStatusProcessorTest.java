//package bg.tu_varna.sit.core.healthinsurance;
//
//import bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate.HealthInsuranceLateFormUpdateStatusRequest;
//import bg.tu_varna.sit.api.inputoutput.healthinsurance.updatelate.HealthInsuranceLateFormUpdateStatusResponse;
//import bg.tu_varna.sit.core.exception.InvalidEnumValueException;
//import bg.tu_varna.sit.core.exception.healthinsurance.HealthInsuranceLateFormIdNotFoundException;
//import bg.tu_varna.sit.core.service.healthinsurance.HealthInsuranceLateFormUpdateStatusProcessor;
//import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
//import bg.tu_varna.sit.persistence.entity.insurance.HealthInsuranceLateForm;
//import bg.tu_varna.sit.persistence.repository.insurance.HealthInsuranceLateFormRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class HealthInsuranceLateUpdateStatusProcessorTest {
//
//    @Mock
//    HealthInsuranceLateFormRepository healthInsuranceLateFormRepository;
//    @InjectMocks
//    HealthInsuranceLateFormUpdateStatusProcessor healthInsuranceLateFormUpdateStatusProcessor;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        HealthInsuranceLateForm mockForm = HealthInsuranceLateForm.builder()
//                .formStatus(FormStatus.SENT)
//                .formId(1L)
//                .build();
//        when(healthInsuranceLateFormRepository.findByIdOptional(1L)).thenReturn(Optional.of(mockForm));
//        when(healthInsuranceLateFormRepository.findById(1L)).thenReturn(mockForm);
//    }
//
//    @Test
//    void testProcess() {
//        HealthInsuranceLateFormUpdateStatusResponse response = healthInsuranceLateFormUpdateStatusProcessor.process(
//                HealthInsuranceLateFormUpdateStatusRequest.builder()
//                        .formId("1")
//                        .formStatus("APPROVED")
//                        .build());
//        assertTrue(response.isSuccess());
//        verify(healthInsuranceLateFormRepository, times(1)).findByIdOptional(1L);
//
//        HealthInsuranceLateForm updatedForm = healthInsuranceLateFormRepository.findById(1L);
//        assertEquals(FormStatus.APPROVED, updatedForm.getFormStatus());
//    }
//
//    @Test
//    void testProcessInvalidForm() {
//        when(healthInsuranceLateFormRepository.findByIdOptional(999L)).thenReturn(Optional.empty());
//
//        HealthInsuranceLateFormIdNotFoundException exception = assertThrows(
//                HealthInsuranceLateFormIdNotFoundException.class,
//                () -> healthInsuranceLateFormUpdateStatusProcessor.process(
//                        HealthInsuranceLateFormUpdateStatusRequest.builder()
//                                .formId("999")
//                                .formStatus("APPROVED")
//                                .build())
//        );
//
//        String expectedMessage = "Health insurance late form with id 999 not found!";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//    @Test
//    void testProcessInvalidEnumValue() {
//        assertThrows(InvalidEnumValueException.class, () -> {
//            healthInsuranceLateFormUpdateStatusProcessor.process(
//                    HealthInsuranceLateFormUpdateStatusRequest.builder()
//                            .formId("1")
//                            .formStatus("INVALID_STATUS")
//                            .build());
//        });
//    }
//}
