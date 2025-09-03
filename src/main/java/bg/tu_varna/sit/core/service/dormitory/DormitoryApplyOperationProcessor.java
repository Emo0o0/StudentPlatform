package bg.tu_varna.sit.core.service.dormitory;

import bg.tu_varna.sit.core.service.student.StudentContext;
import bg.tu_varna.sit.persistence.entity.FamilyMember;
import bg.tu_varna.sit.persistence.entity.PersonalAcademicInfo;
import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryKeepRoomForm;
import bg.tu_varna.sit.persistence.entity.enums.CourseYear;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.repository.dormitory.DormitoryApplyFormRepository;
import bg.tu_varna.sit.persistence.repository.dormitory.DormitoryKeepRoomFormRepository;
import bg.tu_varna.sit.api.inputoutput.dormitory.apply.DormitoryApplyOperation;
import bg.tu_varna.sit.api.inputoutput.dormitory.apply.DormitoryApplyRequest;
import bg.tu_varna.sit.api.inputoutput.dormitory.apply.DormitoryApplyResponse;
import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryApplyForm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.stream.Collectors;

@ApplicationScoped
public class DormitoryApplyOperationProcessor implements DormitoryApplyOperation {

    @Inject
    DormitoryApplyFormRepository dormitoryApplyFormRepository;
    @Inject
    DormitoryKeepRoomFormRepository dormitoryKeepRoomFormRepository;
    @Inject
    StudentContext studentContext;

    @Override
    @Transactional
    public DormitoryApplyResponse process(DormitoryApplyRequest request) {

        DormitoryKeepRoomForm keepRoomForm = null;
        if (request.getKeepRoomFormId() != null) {
            keepRoomForm = dormitoryKeepRoomFormRepository.findById(request.getKeepRoomFormId());
        }

        PersonalAcademicInfo personalAcademicInfo = PersonalAcademicInfo.builder()
                .firstName(request.getPersonalAcademicInfo().getFirstName())
                .secondName(request.getPersonalAcademicInfo().getSecondName())
                .lastName(request.getPersonalAcademicInfo().getLastName())
                .egn(request.getPersonalAcademicInfo().getEgn())
                .phoneNumber(request.getPersonalAcademicInfo().getPhoneNumber())
                .courseYear(CourseYear.valueOf(request.getPersonalAcademicInfo().getCourseYear()))
                .specialty(request.getPersonalAcademicInfo().getSpecialty())
                .facultyNumber(request.getPersonalAcademicInfo().getFacultyNumber())
                .placeOfResidence(request.getPersonalAcademicInfo().getPlaceOfResidence())
                .streetName(request.getPersonalAcademicInfo().getStreetName())
                .streetNumber(request.getPersonalAcademicInfo().getStreetNumber())
                .entrance(request.getPersonalAcademicInfo().getEntrance())
                .floor(request.getPersonalAcademicInfo().getFloor())
                .flatNumber(request.getPersonalAcademicInfo().getFlatNumber())
                .build();


        DormitoryApplyForm dormitoryApplyForm = DormitoryApplyForm.builder()
                .personalAcademicInfo(personalAcademicInfo)
                .buildingNumber(request.getBuildingNumber())
                .roomNumber(request.getRoomNumber())
                .familyMembers(
                        request.getFamilyMembers().stream().map(member -> FamilyMember.builder()
                                        .name(member.getName())
                                        .address(member.getAddress())
                                        .phoneNumber(member.getPhoneNumber())
                                        .dateOfBirth(member.getDateOfBirth())
                                        .familyMemberRelation(member.getFamilyMemberRelation())
                                        .build())
                                .collect(Collectors.toList())
                )
                .formStatus(FormStatus.SENT)
                .keepRoomForm(keepRoomForm)
                .student(studentContext.getCurrentStudent())
                .build();

        dormitoryApplyFormRepository.persist(dormitoryApplyForm);

        return DormitoryApplyResponse.builder()
                .success(true)
                .build();
    }
}
