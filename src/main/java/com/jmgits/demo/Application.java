package com.jmgits.demo;

import com.jmgits.demo.logic.TreatmentRuleFactory;
import com.jmgits.demo.service.TreatmentService;
import com.jmgits.demo.service.impl.TreatmentServiceImpl;
import com.jmgits.demo.transformer.TreatmentInputTransformer;
import com.jmgits.demo.transformer.TreatmentOutputTransformer;
import com.jmgits.demo.view.TreatmentInput;
import com.jmgits.demo.view.TreatmentOutput;

public class Application {

    public static TreatmentService treatmentService = new TreatmentServiceImpl(TreatmentRuleFactory.defaultRules(true));

    public static void main(String[] args) {

        TreatmentInput input = TreatmentInputTransformer.transform(args);

        TreatmentOutput output = treatmentService.applyTreatment(input);

        String result = TreatmentOutputTransformer.transform(output);

        System.out.println(result);
    }
}
