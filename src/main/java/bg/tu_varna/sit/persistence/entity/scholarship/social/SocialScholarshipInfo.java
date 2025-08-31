package bg.tu_varna.sit.persistence.entity.scholarship.social;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SocialScholarshipInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SocialScholarshipType socialType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private List<SocialDocument> socialDocuments = new ArrayList<>();

    private Boolean hasMarriage;
}
