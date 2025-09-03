//package bg.tu_varna.sit.core.dormitory;
//
//import bg.tu_varna.sit.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomRequest;
//import bg.tu_varna.sit.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomResponse;
//import bg.tu_varna.sit.core.service.dormitory.DormitoryKeepRoomOperationProcessor;
//import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryKeepRoomForm;
//import bg.tu_varna.sit.persistence.repository.dormitory.DormitoryKeepRoomFormRepository;
//import io.quarkus.test.security.TestSecurity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//public class DormitoryKeepRoomProcessorTest {
//
//    @Mock
//    DormitoryKeepRoomFormRepository dormitoryKeepRoomFormRepository;
//
//    @InjectMocks
//    DormitoryKeepRoomOperationProcessor dormitoryKeepRoomOperationProcessor;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @TestSecurity
//    void testProcess(){
//
//        DormitoryKeepRoomResponse response = dormitoryKeepRoomOperationProcessor.process(
//                DormitoryKeepRoomRequest.builder()
//                        .buildingNumber(1)
//                        .roomNumber(101)
//                        .build()
//        );
//        assertTrue(response.getSuccess());
//        verify(dormitoryKeepRoomFormRepository, times(1)).persist(any(DormitoryKeepRoomForm.class));
//    }
//
//}
