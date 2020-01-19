package com.jmgits.demo.logic;

import com.jmgits.demo.view.DrugType;
import com.jmgits.demo.view.HealthStatusType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public abstract class TreatmentScenarioGeneric {

    private final HealthStatusType healthStatus;

    public abstract boolean accept(Set<DrugType> drugs);
}
