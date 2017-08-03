package org.lfsmachinelearning.java.simple.gate;

import org.lfsmachinelearning.java.simple.main.Unit;

public class SigmoidGate extends Gate {

    public Unit forward(Unit u0) {
        setU0(u0);
        setUTop(new Unit(getSigmoid(u0.value)));
        return getUTop();
    }

    public void backward() {
        double x = getSigmoid(getU0().value);
        // Calculation of gradient with respect to its input for sigmoid function:
        getU0().grad += (x * (1 - x)) * getUTop().grad;
    }

    private double getSigmoid(double s) {
        return 1 / (1+Math.exp(-s));
    }
}
