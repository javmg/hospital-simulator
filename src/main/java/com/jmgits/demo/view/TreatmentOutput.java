package com.jmgits.demo.view;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class TreatmentOutput {

    private final List<TreatmentOutputItem> items;

}
