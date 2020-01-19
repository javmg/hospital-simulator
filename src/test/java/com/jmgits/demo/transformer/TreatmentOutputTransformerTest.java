package com.jmgits.demo.transformer;

import com.jmgits.demo.view.TreatmentOutput;
import com.jmgits.demo.view.TreatmentOutputItem;
import org.junit.Test;

import java.util.Arrays;

import static com.jmgits.demo.view.HealthStatusType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Unit testing for {@link TreatmentOutputTransformer}
 */
public class TreatmentOutputTransformerTest {

    @Test
    public void testTransform() {

        TreatmentOutput output = new TreatmentOutput(Arrays.asList(
                new TreatmentOutputItem(DEATH, 1),
                new TreatmentOutputItem(DIABETES, 2),
                new TreatmentOutputItem(FEVER, 3),
                new TreatmentOutputItem(HEALTHY, 4),
                new TreatmentOutputItem(TUBERCULOSIS, 5)
        ));

        String result = TreatmentOutputTransformer.transform(output);

        assertThat(result, is("X:1,D:2,F:3,H:4,T:5"));
    }
}
