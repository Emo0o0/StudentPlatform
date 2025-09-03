package bg.tu_varna.sit.core.service.dormitory;

import bg.tu_varna.sit.api.inputoutput.student.getdormitoryforms.*;
import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryApplyForm;
import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryKeepRoomForm;
import bg.tu_varna.sit.persistence.repository.dormitory.DormitoryApplyFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudentGetDormitoryApplyFormsProcessor implements StudentGetDormitoryApplyFormsOperation {

    @Inject
    DormitoryApplyFormRepository dormitoryApplyFormRepository;

    @Override
    public StudentGetDormitoryApplyFormsListResponse process(StudentGetDormitoryApplyFormsRequest request) {
        Long studentId = request.getStudentId() == null ? null : Long.valueOf(request.getStudentId());

        List<DormitoryApplyForm> dormitoryApplyForms = dormitoryApplyFormRepository.getStudentForms(studentId, request.getSpecialty());


        return StudentGetDormitoryApplyFormsListResponse.builder()
                .forms(dormitoryApplyForms.stream()
                        .map(form -> {
                            DormitoryKeepRoomForm keepRoomForm = form.getKeepRoomForm();
                            return StudentGetDormitoryApplyFormsResponse.builder()
                                    .formId(form.getFormId().toString())
                                    .studentFirstName(form.getPersonalAcademicInfo().getFirstName())
                                    .studentLastName(form.getPersonalAcademicInfo().getLastName())
                                    .studentFacultyNumber(form.getPersonalAcademicInfo().getFacultyNumber())
                                    .familyMembers(form.getFamilyMembers().stream()
                                            .map(familyMember -> FamilyMemberDTO.builder()
                                                    .name(familyMember.getName())
                                                    .address(familyMember.getAddress())
                                                    .phoneNumber(familyMember.getPhoneNumber())
                                                    .dateOfBirth(familyMember.getDateOfBirth())
                                                    .familyMemberRelation(familyMember.getFamilyMemberRelation().toString())
                                                    .build())
                                            .collect(Collectors.toList()))
                                    .hasKeepRoomForm(form.getKeepRoomForm() != null)
                                    .keepRoomFormBuildingNumber(keepRoomForm == null ? null : form.getKeepRoomForm().getBuildingNumber())
                                    .keepRoomFormRoomNumber(keepRoomForm == null ? null : form.getKeepRoomForm().getRoomNumber())
                                    .date(form.getDate().toString())
                                    .formStatus(form.getFormStatus().toString())
                                    .build();
                        })
                        .collect(Collectors.toList()))
                .build();
    }
}

