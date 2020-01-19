package com.jmgits.demo.view;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum HealthStatusType {

    DEATH("X"),
    DIABETES("D"),
    FEVER("F"),
    HEALTHY("H"),
    TUBERCULOSIS("T");

    private final String code;

    public static HealthStatusType parseByCode(String code) {

        return Arrays.stream(values())
                .filter(value -> value.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Health status with code '%s' not found!", code))
                );
    }
}
