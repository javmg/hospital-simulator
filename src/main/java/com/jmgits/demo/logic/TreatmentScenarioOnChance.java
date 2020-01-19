package com.jmgits.demo.logic;

import com.jmgits.demo.view.DrugType;
import com.jmgits.demo.view.HealthStatusType;

import java.util.Set;

public class TreatmentScenarioOnChance extends TreatmentScenarioGeneric {

    private static final double DEFAULT_CHANGE = 1.0 / 1_000_000;

    private final double chance;

    public TreatmentScenarioOnChance(HealthStatusType healthStatus) {
        this(healthStatus, DEFAULT_CHANGE);
    }

    public TreatmentScenarioOnChance(HealthStatusType healthStatus, double chance) {
        super(healthStatus);
        this.chance = chance;
    }

    @Override
    public boolean accept(Set<DrugType> drugs) {
        return Math.random() <= chance;
    }
}
