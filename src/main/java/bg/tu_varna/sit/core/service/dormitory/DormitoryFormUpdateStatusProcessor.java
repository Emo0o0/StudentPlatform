package bg.tu_varna.sit.core.service.dormitory;

import bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus.DormitoryFormUpdateStatusOperation;
import bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus.DormitoryFormUpdateStatusRequest;
import bg.tu_varna.sit.api.inputoutput.dormitory.updatestatus.DormitoryFormUpdateStatusResponse;
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

        DormitoryApplyForm form = dormitoryApplyFormRepository.findById(Long.valueOf(request.getFormId()));
        form.updateStatus(FormStatus.valueOf(request.getStatus()));

        return DormitoryFormUpdateStatusResponse.builder()
                .success(true)
                .build();
    }
}
