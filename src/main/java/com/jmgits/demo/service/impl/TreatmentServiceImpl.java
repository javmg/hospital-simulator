package com.jmgits.demo.service.impl;

import com.jmgits.demo.logic.TreatmentDefinitionRule;
import com.jmgits.demo.service.TreatmentService;
import com.jmgits.demo.view.HealthStatusType;
import com.jmgits.demo.view.TreatmentInput;
import com.jmgits.demo.view.TreatmentOutput;
import com.jmgits.demo.view.TreatmentOutputItem;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final Map<HealthStatusType, TreatmentDefinitionRule> rules;

    @Override
    public TreatmentOutput applyTreatment(TreatmentInput input) {

        List<TreatmentOutputItem> treatmentOutputItems = input.getHealthStatuses().stream()

                .map(healthStatus -> rules.get(healthStatus).applyDrugs(input.getDrugs()))

                // using TreeMap to guarantee the same order in the output
                .collect(Collectors.groupingBy(a -> a, TreeMap::new, Collectors.toList()))

                .entrySet().stream()

                .map(entry -> new TreatmentOutputItem(entry.getKey(), entry.getValue().size()))

                .collect(Collectors.toList());

        return new TreatmentOutput(treatmentOutputItems);
    }
}
