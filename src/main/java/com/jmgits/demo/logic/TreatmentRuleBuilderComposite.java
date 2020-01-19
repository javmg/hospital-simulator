package com.jmgits.demo.logic;

import com.jmgits.demo.view.DrugType;
import com.jmgits.demo.view.HealthStatusType;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class TreatmentRuleBuilderComposite {

    private final Map<HealthStatusType, TreatmentDefinitionRule> rules = new EnumMap<>(HealthStatusType.class);

    private final Set<TreatmentScenarioGeneric> then = new HashSet<>();

    private TreatmentRuleBuilderSimple ruleBuilder;

    private Set<DrugType> withDrugs;

    public TreatmentRuleBuilderComposite withDrugs(DrugType... drugs) {
        this.withDrugs = new HashSet<>(Arrays.asList(drugs));

        return this;
    }

    public TreatmentRuleBuilderComposite then(HealthStatusType healthStatus) {
        then.add(new TreatmentScenarioOnDrugs(healthStatus, withDrugs));

        return this;
    }

    public TreatmentRuleBuilderSimple when(HealthStatusType healthStatus) {

        this.ruleBuilder = new TreatmentRuleBuilderSimple(this, healthStatus);

        then.forEach(this.ruleBuilder::then);

        return this.ruleBuilder;
    }

    public TreatmentRuleBuilderComposite next() {
        TreatmentDefinitionRule rule = this.ruleBuilder.build();
        rules.put(rule.getInitialHealthStatus(), rule);

        return this;
    }

    public Map<HealthStatusType, TreatmentDefinitionRule> build() {
        return rules;
    }
}
