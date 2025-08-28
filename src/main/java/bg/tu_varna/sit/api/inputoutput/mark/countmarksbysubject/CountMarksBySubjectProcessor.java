package bg.tu_varna.sit.api.inputoutput.mark.countmarksbysubject;

import bg.tu_varna.sit.persistence.repository.MarkRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CountMarksBySubjectProcessor implements CountMarksBySubjectOperation {

    @Inject
    MarkRepository markRepository;

    @Override
    public CountMarksBySubjectResponse process(CountMarksBySubjectRequest request) {

        long marks = markRepository.countByMark(request.getSubject(), request.getMark());

        return CountMarksBySubjectResponse.builder()
                .count(marks)
                .build();
    }
}
