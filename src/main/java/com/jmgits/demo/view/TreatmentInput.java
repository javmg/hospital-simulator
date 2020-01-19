package com.jmgits.demo.view;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Getter
public class TreatmentInput {

    private final List<HealthStatusType> healthStatuses;

    private final Set<DrugType> drugs;
}
