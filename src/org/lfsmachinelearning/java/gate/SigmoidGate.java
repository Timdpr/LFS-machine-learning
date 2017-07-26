package org.lfsmachinelearning.java.gate;

import org.lfsmachinelearning.java.main.Unit;

public class SigmoidGate implements Gate {
    /**
     * uTop stores gate output value; later is given 'top pull' gradient for backwards pass
     */
    private Unit uTop = new Unit();
    private Unit u0 = new Unit();

    public Unit forward(Unit u0) {
        this.u0 = u0;
        this.uTop = new Unit(getSigmoid(u0.value));
        return this.uTop;
    }

    public void backward() {
        double x = getSigmoid(this.u0.value);
        // Calculation of gradient with respect to its input for sigmoid function:
        this.u0.grad += (x * (1 - x)) * this.uTop.grad; 
    }

    private double getSigmoid(double s) {
        return 1 / (1+Math.exp(-s));
    }
}
