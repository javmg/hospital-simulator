package com.jmgits.demo.logic;

import org.junit.Test;

import java.util.Collections;

import static com.jmgits.demo.view.HealthStatusType.HEALTHY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Unit testing for {@link TreatmentScenarioOnChance}
 */
public class TreatmentScenarioOnChanceTest {

    @Test
    public void testAcceptAlwaysFalse(){

        boolean result = new TreatmentScenarioOnChance(HEALTHY, 0).accept(Collections.emptySet());

        assertThat(result, is(false));
    }

    @Test
    public void testAcceptAlwaysTrue(){

        boolean result = new TreatmentScenarioOnChance(HEALTHY, 1).accept(Collections.emptySet());

        assertThat(result, is(true));
    }
}
