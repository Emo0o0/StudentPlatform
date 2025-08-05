package com.example.core.service.dormitory;

import com.example.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomOperation;
import com.example.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomRequest;
import com.example.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomResponse;
import com.example.persistence.entity.dormitory.DormitoryKeepRoomForm;
import com.example.persistence.entity.enums.FormStatus;
import com.example.persistence.repository.DormitoryKeepRoomFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DormitoryKeepRoomOperationProcessor implements DormitoryKeepRoomOperation {

    @Inject
    DormitoryKeepRoomFormRepository dormitoryKeepRoomFormRepository;

    @Override
    @Transactional
    public DormitoryKeepRoomResponse process(DormitoryKeepRoomRequest request) {

        DormitoryKeepRoomForm dormitoryKeepRoomForm = DormitoryKeepRoomForm.builder()
                .buildingNumber(request.getBuildingNumber())
                .roomNumber(request.getRoomNumber())
                .formStatus(FormStatus.SENT)
                .build();

        dormitoryKeepRoomFormRepository.persist(dormitoryKeepRoomForm);

        return DormitoryKeepRoomResponse.builder()
                .success(true)
                .build();
    }
}
