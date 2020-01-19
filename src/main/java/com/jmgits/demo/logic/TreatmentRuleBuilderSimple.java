package com.jmgits.demo.logic;

import com.jmgits.demo.view.DrugType;
import com.jmgits.demo.view.HealthStatusType;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
public class TreatmentRuleBuilderSimple {

    private final TreatmentRuleBuilderComposite treatmentRulesBuilderSimple;

    private final HealthStatusType initialHealthStatus;

    private HealthStatusType withoutDrugs;

    private Set<DrugType> withDrugs = null;

    private boolean withChance = false;

    private final List<TreatmentScenarioGeneric> scenarios = new ArrayList<>();

    public TreatmentDefinitionRule build() {

        withoutDrugs = withoutDrugs == null ? initialHealthStatus : withoutDrugs;

        return new TreatmentDefinitionRule(initialHealthStatus, withoutDrugs, scenarios);
    }

    public TreatmentRuleBuilderSimple withoutDrugs(HealthStatusType outcome) {

        this.withoutDrugs = outcome;

        return this;
    }

    public TreatmentRuleBuilderSimple withDrugs(DrugType... drugs) {

        this.withDrugs = new HashSet<>(Arrays.asList(drugs));

        return this;
    }

    public TreatmentRuleBuilderSimple withChance(boolean withChance) {
        this.withChance = withChance;

        return this;
    }

    public TreatmentRuleBuilderSimple then(HealthStatusType healthStatus) {

        if (withDrugs != null || withChance) {

            TreatmentScenarioGeneric scenario = withDrugs != null ?
                    new TreatmentScenarioOnDrugs(healthStatus, withDrugs) :
                    new TreatmentScenarioOnChance(healthStatus);

            scenarios.add(scenario);
        }

        return this;
    }

    void then(TreatmentScenarioGeneric treatmentScenario) {
        this.scenarios.add(treatmentScenario);
    }

    public TreatmentRuleBuilderComposite and() {
        return treatmentRulesBuilderSimple.next();
    }
}
