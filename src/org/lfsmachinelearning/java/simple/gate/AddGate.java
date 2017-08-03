package org.lfsmachinelearning.java.simple.gate;

import org.lfsmachinelearning.java.simple.main.Unit;

public class AddGate extends Gate {

    public Unit forward(Unit u0, Unit u1) {
        setU0(u0);
        setU1(u1);
        setUTop(new Unit(u0.value + u1.value)); // uTop value = gate output
        return getUTop();
    }

    public void backward() {
        // For +, gradient of inputs is 1
        getU0().grad += 1 * getUTop().grad;
        getU1().grad += 1 * getUTop().grad;
    }
}
