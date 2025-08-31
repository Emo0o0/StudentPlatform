package bg.tu_varna.sit.core.dormitory;

import bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus.DormitoryFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus.DormitoryFormUpdateStatusResponse;
import bg.tu_varna.sit.core.exception.InvalidEnumValueException;
import bg.tu_varna.sit.core.exception.dormitory.DormitoryApplyFormIdNotFoundException;
import bg.tu_varna.sit.core.service.dormitory.DormitoryFormUpdateStatusProcessor;
import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryApplyForm;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.repository.dormitory.DormitoryApplyFormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DormitoryFormUpdateStatusProcessorTest {

    @Mock
    DormitoryApplyFormRepository dormitoryApplyFormRepository;
    @InjectMocks
    DormitoryFormUpdateStatusProcessor dormitoryFormUpdateStatusProcessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        DormitoryApplyForm mockForm = DormitoryApplyForm.builder()
                .formId(1L)
                .build();
        when(dormitoryApplyFormRepository.findById(1L)).thenReturn(mockForm);
    }

    @Test
    void testProcess() {
        DormitoryFormUpdateStatusResponse response = dormitoryFormUpdateStatusProcessor.process(DormitoryFormUpdateStatusRequest
                .builder()
                .formId("1")
                .status("APPROVED")
                .build());
        assertTrue(response.isSuccess());
        verify(dormitoryApplyFormRepository, times(1)).findById(1L);

        DormitoryApplyForm updatedForm = dormitoryApplyFormRepository.findById(1L);
        assertEquals(FormStatus.APPROVED, updatedForm.getFormStatus());
    }

    @Test
    void testProcessInvalidForm() {
        when(dormitoryApplyFormRepository.findByIdOptional(999L)).thenReturn(Optional.empty());

        DormitoryApplyFormIdNotFoundException exception = assertThrows(
                DormitoryApplyFormIdNotFoundException.class,
                () -> dormitoryFormUpdateStatusProcessor.process(
                        DormitoryFormUpdateStatusRequest.builder()
                                .formId("999")
                                .status("APPROVED")
                                .build()
                )
        );
        assertEquals("Dormitory apply form with id 999 not found!", exception.getMessage());
    }
    @Test
    void testProcessInvalidEnumValue() {
        DormitoryApplyForm mockForm = DormitoryApplyForm.builder()
                .formId(1L)
                .build();
        when(dormitoryApplyFormRepository.findByIdOptional(1L)).thenReturn(Optional.of(mockForm));

        InvalidEnumValueException exception = assertThrows(
                InvalidEnumValueException.class,
                () -> dormitoryFormUpdateStatusProcessor.process(
                        DormitoryFormUpdateStatusRequest.builder()
                                .formId("1")
                                .status("INVALID_STATUS")
                                .build()
                )
        );

        assertEquals("Invalid status value: INVALID_STATUS", exception.getMessage());
    }

}
