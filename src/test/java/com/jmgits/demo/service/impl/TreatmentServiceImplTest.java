package com.jmgits.demo.service.impl;

import com.jmgits.demo.logic.TreatmentRuleFactory;
import com.jmgits.demo.service.TreatmentService;
import com.jmgits.demo.view.TreatmentInput;
import com.jmgits.demo.view.TreatmentOutput;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static com.jmgits.demo.view.DrugType.*;
import static com.jmgits.demo.view.HealthStatusType.*;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Unit testing for {@link TreatmentServiceImpl}
 */
public class TreatmentServiceImplTest {

    // we disable the Flying Spaghetti Monster option to avoid random errors in an hypothetical CI/ CD env
    public TreatmentService treatmentService = new TreatmentServiceImpl(TreatmentRuleFactory.defaultRules(false));

    @Test
    public void whenParacetamolAndAspirinAreApplied_thenAllPatientsDie() {

        TreatmentInput input = new TreatmentInput(
                Arrays.asList(FEVER, FEVER, HEALTHY, DIABETES, TUBERCULOSIS, DEATH),
                new HashSet<>(Arrays.asList(ASPIRIN, ANTIBIOTIC, INSULIN, PARACETAMOL))
        );

        TreatmentOutput output = treatmentService.applyTreatment(input);

        assertThat(output.getItems().get(0).getHealthStatus(), is(DEATH));
        assertThat(output.getItems().get(0).getNumPatients(), is(6));
    }

    @Test
    public void whenDead_thenPatientsRemainDead() {

        TreatmentInput input = new TreatmentInput(
                singletonList(DEATH),
                new HashSet<>()
        );

        TreatmentOutput output = treatmentService.applyTreatment(input);

        assertThat(output.getItems().get(0).getHealthStatus(), is(DEATH));
        assertThat(output.getItems().get(0).getNumPatients(), is(1));
    }

    @Test
    public void whenNoInsulinIsApplied_thenPatientsWithDiabetesDie() {

        TreatmentInput input = new TreatmentInput(
                Arrays.asList(DIABETES, FEVER, DIABETES),
                new HashSet<>()
        );

        TreatmentOutput output = treatmentService.applyTreatment(input);

        assertThat(output.getItems().get(0).getHealthStatus(), is(DEATH));
        assertThat(output.getItems().get(0).getNumPatients(), is(2));

        assertThat(output.getItems().get(1).getHealthStatus(), is(FEVER));
        assertThat(output.getItems().get(1).getNumPatients(), is(1));
    }

    @Test
    public void whenInsulinIsApplied_thenPatientsWithDiabetesSurvive() {

        TreatmentInput input = new TreatmentInput(
                Arrays.asList(DIABETES, DIABETES, DIABETES),
                singleton(INSULIN)
        );

        TreatmentOutput output = treatmentService.applyTreatment(input);

        assertThat(output.getItems().get(0).getHealthStatus(), is(DIABETES));
        assertThat(output.getItems().get(0).getNumPatients(), is(3));
    }

    @Test
    public void whenAspirinIsApplied_thenPatientsWithFeverGetHealthy() {

        TreatmentInput input = new TreatmentInput(
                singletonList(FEVER),
                singleton(ASPIRIN)
        );

        TreatmentOutput output = treatmentService.applyTreatment(input);

        assertThat(output.getItems().get(0).getHealthStatus(), is(HEALTHY));
        assertThat(output.getItems().get(0).getNumPatients(), is(1));
    }

    @Test
    public void whenParacetamolIsApplied_thenPatientsWithFeverGetHealthy() {

        TreatmentInput input = new TreatmentInput(
                singletonList(FEVER),
                singleton(PARACETAMOL)
        );

        TreatmentOutput output = treatmentService.applyTreatment(input);

        assertThat(output.getItems().get(0).getHealthStatus(), is(HEALTHY));
        assertThat(output.getItems().get(0).getNumPatients(), is(1));
    }

    @Test
    public void whenAntibioticsAndInsulinAreApplied_thenHealthyPatientsGetFever() {

        TreatmentInput input = new TreatmentInput(
                singletonList(HEALTHY),
                new HashSet<>(Arrays.asList(ANTIBIOTIC, INSULIN))
        );

        TreatmentOutput output = treatmentService.applyTreatment(input);

        assertThat(output.getItems().get(0).getHealthStatus(), is(FEVER));
        assertThat(output.getItems().get(0).getNumPatients(), is(1));
    }

    @Test
    public void whenAntibioticsAreApplied_thenPatientsWithTuberculosisGetHealthy() {

        TreatmentInput input = new TreatmentInput(
                singletonList(TUBERCULOSIS),
                singleton(ANTIBIOTIC)
        );

        TreatmentOutput output = treatmentService.applyTreatment(input);

        assertThat(output.getItems().get(0).getHealthStatus(), is(HEALTHY));
        assertThat(output.getItems().get(0).getNumPatients(), is(1));
    }
}
