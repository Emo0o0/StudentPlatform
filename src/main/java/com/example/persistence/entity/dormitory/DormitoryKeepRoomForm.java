package com.example.persistence.entity.dormitory;

import com.example.persistence.entity.enums.FormStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DormitoryKeepRoomForm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long formId;
    private Integer buildingNumber;
    private Integer roomNumber;
    @Enumerated(EnumType.STRING)
    private FormStatus formStatus;
    @CreationTimestamp
    private Timestamp date;
}
