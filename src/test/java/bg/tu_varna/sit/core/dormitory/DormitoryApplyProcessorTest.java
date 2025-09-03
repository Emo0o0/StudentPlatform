//package bg.tu_varna.sit.core.dormitory;
//
//import bg.tu_varna.sit.api.PersonalAcademicInfoDTO;
//import bg.tu_varna.sit.api.inputoutput.dormitory.apply.DormitoryApplyRequest;
//import bg.tu_varna.sit.api.inputoutput.dormitory.apply.DormitoryApplyResponse;
//import bg.tu_varna.sit.api.inputoutput.dormitory.apply.FamilyMemberDto;
//import bg.tu_varna.sit.core.service.dormitory.DormitoryApplyOperationProcessor;
//import bg.tu_varna.sit.core.service.student.StudentContext;
//import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
//import bg.tu_varna.sit.persistence.entity.Student;
//import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryApplyForm;
//import bg.tu_varna.sit.persistence.entity.enums.DegreeLevel;
//import bg.tu_varna.sit.persistence.entity.enums.FamilyMemberRelation;
//import bg.tu_varna.sit.persistence.repository.dormitory.DormitoryApplyFormRepository;
//import io.quarkus.test.security.TestSecurity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class DormitoryApplyProcessorTest {
//
//    @Mock
//    DormitoryApplyFormRepository dormitoryApplyFormRepository;
//
//    @Mock
//    StudentContext studentContext;
//    @InjectMocks
//    DormitoryApplyOperationProcessor dormitoryApplyOperationProcessor;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        Student mockedStudent = mock(Student.class);
//
//        when(mockedStudent.getId()).thenReturn(1L);
//        when(mockedStudent.getPersonalAcademicInfo()).thenReturn(mock(PersonalAcademicInfo.class));
//
//        when(studentContext.getCurrentStudent()).thenReturn(mockedStudent);
//    }
//
//    @Test
//    @TestSecurity
//    void testProcess(){
//
//        List<FamilyMemberDto> familyMembers = new ArrayList<>();
//
//        FamilyMemberDto familyMember = FamilyMemberDto.builder()
//                .name("Emo Kolev")
//                .address("Varna")
//                .phoneNumber("0888123456")
//                .dateOfBirth("2000-01-01")
//                .familyMemberRelation(FamilyMemberRelation.BROTHER)
//                .build();
//        familyMembers.add(familyMember);
//
//        DormitoryApplyRequest request = DormitoryApplyRequest.builder()
//                .personalAcademicInfo(PersonalAcademicInfoDTO.builder()
//                        .email("E@E")
//                        .firstName("Emo")
//                        .lastName("Kolev")
//                        .egn("1234567890")
//                        .address("Varna")
//                        .phoneNumber("0888123456")
//                        .placeOfResidence("Varna")
//                        .streetName("SomeStreet")
//                        .streetNumber(12)
//                        .entrance("A")
//                        .floor(1)
//                        .flatNumber(1)
//                        .facultyNumber("123456")
//                        .courseYear("II")
//                        .semester("4")
//                        .degreeLevel("BACHELOR")
//                        .faculty("FMI")
//                        .specialty("KSI")
//                        .studentGroup(1)
//                        .subGroup('A')
//                        .build())
//                .degreeLevel(DegreeLevel.BACHELOR.name())
//                .familyMembers(familyMembers)
//                .buildingNumber(1)
//                .roomNumber(101)
//                .keepRoomFormId(null)
//                .build();
//
//        DormitoryApplyResponse response = dormitoryApplyOperationProcessor.process(request);
//
//        assertTrue(response.getSuccess());
//        verify(dormitoryApplyFormRepository, times(1)).persist(any(DormitoryApplyForm.class));
//    }
//}
