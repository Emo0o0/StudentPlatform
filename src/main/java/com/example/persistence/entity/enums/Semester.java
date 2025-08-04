package com.example.persistence.entity.enums;

import lombok.Getter;

@Getter
public enum Semester {
    FIRST(1), SECOND(2), THIRD(3), FOURTH(4), FIFTH(5),
    SIXTH(6), SEVENTH(7), EIGHTH(8), NINTH(9), TENTH(10);

    private final int number;

    Semester(int number) {
        this.number = number;
    }

}
