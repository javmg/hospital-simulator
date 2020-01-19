package com.jmgits.demo.transformer;

import com.jmgits.demo.view.DrugType;
import com.jmgits.demo.view.HealthStatusType;
import com.jmgits.demo.view.TreatmentInput;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class TreatmentInputTransformer {

    public static TreatmentInput transform(String[] parameters) {

        if (parameters == null || parameters.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments!");
        }

        List<HealthStatusType> healthStatuses = Arrays.stream(parameters[0].split(","))
                .filter(cad -> !cad.isEmpty())
                .map(HealthStatusType::parseByCode)
                .collect(Collectors.toList());

        Set<DrugType> drugs = Arrays.stream(parameters[1].split(","))
                .filter(cad -> !cad.isEmpty())
                .map(DrugType::parseByCode)
                .collect(Collectors.toSet());

        return new TreatmentInput(healthStatuses, drugs);
    }
}
