package com.example.core.service.dormitory;

import com.example.api.inputoutput.dormitory.apply.DormitoryApplyOperation;
import com.example.api.inputoutput.dormitory.apply.DormitoryApplyRequest;
import com.example.api.inputoutput.dormitory.apply.DormitoryApplyResponse;
import com.example.core.service.student.StudentContext;
import com.example.persistence.entity.*;
import com.example.persistence.entity.dormitory.DormitoryApplyForm;
import com.example.persistence.entity.dormitory.DormitoryKeepRoomForm;
import com.example.persistence.entity.enums.FormStatus;
import com.example.persistence.repository.dormitory.DormitoryApplyFormRepository;
import com.example.persistence.repository.dormitory.DormitoryKeepRoomFormRepository;
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

        DormitoryApplyForm dormitoryApplyForm = DormitoryApplyForm.builder()
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
