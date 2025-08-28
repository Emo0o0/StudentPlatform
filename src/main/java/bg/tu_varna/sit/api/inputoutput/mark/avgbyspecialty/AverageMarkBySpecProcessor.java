package bg.tu_varna.sit.api.inputoutput.mark.avgbyspecialty;

import bg.tu_varna.sit.persistence.repository.MarkRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AverageMarkBySpecProcessor implements AverageMarkBySpecOperation {

    @Inject
    MarkRepository markRepository;

    @Override
    public AverageMarkBySpecResponse process(AverageMarkBySpecRequest request) {

        double averageGrade = markRepository.avgGradeBySubject(request.getSubject());

        return AverageMarkBySpecResponse.builder()
                .avgGrade(averageGrade)
                .build();
    }
}
