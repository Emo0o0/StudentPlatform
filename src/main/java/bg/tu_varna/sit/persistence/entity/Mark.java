package bg.tu_varna.sit.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String subject;
    private String mark;
    @Enumerated(EnumType.STRING)
    private SubjectStatus subjectStatus;
    private Boolean hasAttendedExam;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

}
