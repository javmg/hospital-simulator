package com.jmgits.demo.transformer;

import com.jmgits.demo.view.TreatmentInput;
import org.junit.Test;

import static com.jmgits.demo.view.DrugType.*;
import static com.jmgits.demo.view.HealthStatusType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Unit testing for {@link TreatmentInputTransformer}
 */
public class TreatmentInputTransformerTest {

    @Test
    public void testTransform() {

        java.lang.String[] args = new java.lang.String[2];

        args[0] = "F,H,D,T,X";
        args[1] = "As,An,I,P";

        TreatmentInput result = TreatmentInputTransformer.transform(args);

        assertThat(result.getHealthStatuses(), contains(FEVER, HEALTHY, DIABETES, TUBERCULOSIS, DEATH));
        assertThat(result.getDrugs(), containsInAnyOrder(ASPIRIN, ANTIBIOTIC, INSULIN, PARACETAMOL));
    }
}
