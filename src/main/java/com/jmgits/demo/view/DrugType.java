package com.jmgits.demo.view;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum DrugType {

    ANTIBIOTIC("An"),
    ASPIRIN("As"),
    INSULIN("I"),
    PARACETAMOL("P"),

    ;

    private final String code;

    public static DrugType parseByCode(String code) {

        return Arrays.stream(values())
                .filter(value -> value.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Drug with code '%s' not found!", code))
                );
    }

}
