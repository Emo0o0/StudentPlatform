package com.example.api.inputoutput.student;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyAverageDTO {
    private String courseYear; // null = overall average
    private double averageGrade;
}
