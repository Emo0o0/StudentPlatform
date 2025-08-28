package bg.tu_varna.sit.persistence.entity.enums;

import lombok.Getter;

@Getter
public enum DegreeLevel {

    BACHELOR("Бакалавър"),
    MASTER("Магистър"),
    PROFESSIONAL_BACHELOR("Професионален бакалавър");

    private final String displayName;

    DegreeLevel(String displayName) {
        this.displayName = displayName;
    }
}
