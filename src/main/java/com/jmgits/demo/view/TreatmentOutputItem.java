package com.jmgits.demo.view;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TreatmentOutputItem {

    private final HealthStatusType healthStatus;

    private final Integer numPatients;
}
