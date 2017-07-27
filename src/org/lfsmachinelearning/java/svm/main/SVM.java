package org.lfsmachinelearning.java.svm.main;

import org.lfsmachinelearning.java.simple.main.Unit;

public class SVM {
    public Unit a = new Unit(1.0, 0.0); // random initial parameters
    public Unit b = new Unit(-2.0, 0.0);
    public Unit c = new Unit(-1.0, 0.0);
    public Unit unitOut;

    public Circuit circuit = new Circuit();

    public Unit forward(Unit x, Unit y) {
        this.unitOut = this.circuit.forward(x, y, a, b, c);
        return unitOut;
    }

    public void backward(double label) { // label is +1 or -1
        // reset pulls
        a.grad = 0;
        b.grad = 0;
        c.grad = 0;

        // compute pull based on circuit output.
        // if score too low, pull up, too high, pull down:
        double pull = 0.0;
        pull = (this.unitOut.value < 1) ? 1 : -1;
        this.circuit.backward(pull);

        // add regularization pull for parameters: towards 0 and proportional to value
        a.grad += -a.value;
        b.grad += -b.value;
    }

    public void learnFrom(Unit x, Unit y, double label) {
        this.forward(x, y); // forward pass sets .value in all Units
        this.backward(label); // backward pass sets .grad in all Units
        this.parameterUpdate(); // parameters respond to tug
    }

    public void parameterUpdate() {
        double stepSize = 0.01;
        a.value += stepSize * a.grad;
        b.value += stepSize * b.grad;
        c.value += stepSize * c.grad;
    }
}
