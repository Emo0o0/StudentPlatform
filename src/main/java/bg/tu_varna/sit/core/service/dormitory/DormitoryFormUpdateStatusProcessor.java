package bg.tu_varna.sit.core.service.dormitory;

import bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus.DormitoryFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus.DormitoryFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus.DormitoryFormUpdateStatusResponse;
import bg.tu_varna.sit.core.exception.InvalidEnumValueException;
import bg.tu_varna.sit.core.exception.dormitory.DormitoryApplyFormIdNotFoundException;
import bg.tu_varna.sit.persistence.entity.dormitory.DormitoryApplyForm;
import bg.tu_varna.sit.persistence.entity.enums.FormStatus;
import bg.tu_varna.sit.persistence.repository.dormitory.DormitoryApplyFormRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DormitoryFormUpdateStatusProcessor implements DormitoryFormUpdateStatusOperation {

    @Inject
    DormitoryApplyFormRepository dormitoryApplyFormRepository;

    @Override
    @Transactional
    public DormitoryFormUpdateStatusResponse process(DormitoryFormUpdateStatusRequest request) {

        DormitoryApplyForm form = dormitoryApplyFormRepository.findByIdOptional(Long.valueOf(request.getFormId()))
                .orElseThrow(()-> new DormitoryApplyFormIdNotFoundException("Dormitory apply form with id " + request.getFormId() + " not found!"));
        try{
            form.updateStatus(FormStatus.valueOf(request.getStatus()));
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid status value: " + request.getStatus());
        }

        return DormitoryFormUpdateStatusResponse.builder()
                .success(true)
                .build();
    }
}
