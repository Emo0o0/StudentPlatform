package bg.tu_varna.sit.persistence.entity.scholarship.meritincome;

import lombok.Getter;

@Getter
public enum FamilyStatus {
    MARRIED("Семейни"),
    SINGLE("Несемейни");

    private final String displayName;

    FamilyStatus(String displayName) {
        this.displayName = displayName;
    }

}
