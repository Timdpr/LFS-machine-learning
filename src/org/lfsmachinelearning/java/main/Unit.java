package org.lfsmachinelearning.java.main;

/**
 * Stores a value and a gradient. If parameter not given, initialises to 0.0
 */
public class Unit {
    public double value;
    public double grad = 0.0;

    public Unit(double value, double grad) {
        this.value = value;
        this.grad = grad;
    }

    public Unit(double value) {
        this.value = value;
    }

    public Unit() {
        this.value = 0.0;
    }
}
