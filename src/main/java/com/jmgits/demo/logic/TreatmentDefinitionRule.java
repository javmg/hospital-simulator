package com.jmgits.demo.logic;

import com.jmgits.demo.view.DrugType;
import com.jmgits.demo.view.HealthStatusType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jmgits.demo.view.HealthStatusType.DEATH;

@RequiredArgsConstructor
@Getter
public class TreatmentDefinitionRule {

    private final HealthStatusType initialHealthStatus;

    private final HealthStatusType defaultHealthStatus;

    private final List<TreatmentScenarioGeneric> scenarios;

    public HealthStatusType applyDrugs(Set<DrugType> drugs) {

        List<TreatmentScenarioGeneric> scenarios = getScenarios(drugs);

        return scenarios.isEmpty() ? this.defaultHealthStatus : scenarios.get(0).getHealthStatus();
    }

    //
    // private

    private List<TreatmentScenarioGeneric> getScenarios(Set<DrugType> drugs) {

        return scenarios.stream()

                .filter(treatmentScenario -> treatmentScenario.accept(drugs))

                .sorted((a, b) -> {

                    if (a.getHealthStatus() == DEATH) {
                        return -1;
                    } else if (b.getHealthStatus() == DEATH) {
                        return 1;
                    }

                    return 0;
                })

                .collect(Collectors.toList());
    }
}
