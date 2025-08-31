package bg.tu_varna.sit.core.service.dormitory;

import bg.tu_varna.sit.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomOperation;
import bg.tu_varna.sit.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomRequest;
import bg.tu_varna.sit.api.inputoutput.dormitory.keeproom.DormitoryKeepRoomResponse;
import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryKeepRoomForm;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.repository.dormitory.DormitoryKeepRoomFormRepository;
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
                .formId(dormitoryKeepRoomForm.getFormId())
                .success(true)
                .build();
    }
}
