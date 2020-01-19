package com.jmgits.demo.transformer;

import com.jmgits.demo.view.HealthStatusType;
import com.jmgits.demo.view.TreatmentOutput;
import com.jmgits.demo.view.TreatmentOutputItem;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class TreatmentOutputTransformer {

    public static String transform(TreatmentOutput output) {

        Map<HealthStatusType, Integer> mapHealthStatusAndNumPatients = output.getItems().stream()
                .collect(Collectors.toMap(TreatmentOutputItem::getHealthStatus, TreatmentOutputItem::getNumPatients));

        return Arrays.stream(HealthStatusType.values())

                .map(healthStatus -> {

                    Integer numPatients = mapHealthStatusAndNumPatients.getOrDefault(healthStatus, 0);

                    return String.format("%s:%s", healthStatus.getCode(), numPatients);
                })

                .collect(Collectors.joining(","));
    }
}
