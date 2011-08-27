package com.sabre.myfares.spike.ui.components;

import org.vaadin.risto.stepper.IntStepper;

@SuppressWarnings("serial")
public class QuantitySpinner extends IntStepper{

    public QuantitySpinner(int minValue, int maxValue, int actualValue, int step) {
        super();
        setMinValue(minValue);
        setMaxValue(maxValue);
        setStepAmount(step);
        setValue(actualValue);
    }

}
