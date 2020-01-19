package com.jmgits.demo.logic;

import com.jmgits.demo.view.DrugType;
import com.jmgits.demo.view.HealthStatusType;

import java.util.Set;

public class TreatmentScenarioOnDrugs extends TreatmentScenarioGeneric {

    private final Set<DrugType> drugs;

    public TreatmentScenarioOnDrugs(HealthStatusType healthStatus, Set<DrugType> drugs) {
        super(healthStatus);
        this.drugs = drugs;
    }

    @Override
    public boolean accept(Set<DrugType> drugs) {
        return this.drugs != null && drugs.containsAll(this.drugs);
    }
}
