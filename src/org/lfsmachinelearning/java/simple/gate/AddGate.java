package org.lfsmachinelearning.java.simple.gate;

import org.lfsmachinelearning.java.simple.main.Unit;

public class AddGate {
    /**
     * uTop stores gate output value; later is given 'top pull' gradient for backwards pass
     */
    private final Unit uTop = new Unit();
    private Unit u0 = new Unit();
    private Unit u1 = new Unit();

    public Unit forward(Unit u0, Unit u1) {
        this.u0 = u0;
        this.u1 = u1;
        this.uTop.value = u0.value + u1.value; // uTop value = gate output
        return this.uTop;
    }

    public void backward() {
        // For +, gradient of inputs is 1
        this.u0.grad += 1 * this.uTop.grad;
        this.u1.grad += 1 * this.uTop.grad;
    }
}
