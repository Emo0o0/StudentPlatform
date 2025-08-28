package com.example.api.inputoutput.student;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectStatsDTO {

    private String subject;
    private Double averageGrade;
    private Long sixes;
    private Long fives;
    private Long fours;
    private Long threes;
    private Long twos;
    private Long notAttended;
    private Long eligible;
    private Long ineligible;
}
