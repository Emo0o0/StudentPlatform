package com.example.persistence.entity.enums;

import lombok.Getter;

@Getter
public enum Faculty {

    MECHANICAL_TECHNOLOGICAL("Машинно-технологичен факултет"),
    ELECTRICAL("Електротехнически факултет"),
    SHIPBUILDING("Корабостроителен факултет"),
    COMPUTER_AUTOMATION("Факултет по изчислителна техника и автоматизация"),
    DOBRUDJA_COLLEGE("Добруджански технологичен колеж"),
    TU_COLLEGE("Колеж в структурата на ТУ-Варна");

    private final String displayName;

    Faculty(String displayName){
        this.displayName=displayName;
    }
}
