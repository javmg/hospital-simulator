package com.jmgits.demo.service;

import com.jmgits.demo.view.TreatmentInput;
import com.jmgits.demo.view.TreatmentOutput;

public interface TreatmentService {

    TreatmentOutput applyTreatment(TreatmentInput input);
}
