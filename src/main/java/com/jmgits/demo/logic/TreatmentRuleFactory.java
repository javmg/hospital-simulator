package com.jmgits.demo.logic;

import com.jmgits.demo.view.HealthStatusType;

import java.util.Map;

import static com.jmgits.demo.view.DrugType.*;
import static com.jmgits.demo.view.HealthStatusType.*;

public class TreatmentRuleFactory {

    public static Map<HealthStatusType, TreatmentDefinitionRule> defaultRules(boolean withFlyingSpaghettiMonster) {

        return new TreatmentRuleBuilderComposite()

                .withDrugs(ASPIRIN, PARACETAMOL).then(DEATH)

                .when(DEATH)

                .withChance(withFlyingSpaghettiMonster).then(HEALTHY)

                .and()

                .when(DIABETES)

                .withoutDrugs(DEATH)

                .withDrugs(INSULIN).then(DIABETES)

                .and()

                .when(FEVER)

                .withDrugs(ASPIRIN).then(HEALTHY)

                .withDrugs(PARACETAMOL).then(HEALTHY)

                .and()

                .when(HEALTHY)

                .withDrugs(INSULIN, ANTIBIOTIC).then(FEVER)

                .and()

                .when(TUBERCULOSIS)

                .withDrugs(ANTIBIOTIC).then(HEALTHY)

                .and()

                .build();
    }
}
