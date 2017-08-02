package org.lfsmachinelearning.java.svm.main;

import org.lfsmachinelearning.java.simple.gate.AddGate;
import org.lfsmachinelearning.java.simple.gate.MultiplyGate;
import org.lfsmachinelearning.java.simple.main.Unit;

public class Circuit {
    private final MultiplyGate mulg0 = new MultiplyGate();
    private final MultiplyGate mulg1 = new MultiplyGate();
    private final AddGate addg0 = new AddGate();
    private final AddGate addg1 = new AddGate();

    private Unit axpbypc;
    
    public Unit forward(Unit x, Unit y, Unit a, Unit b, Unit c) {
        Unit ax = this.mulg0.forward(a, x);
        Unit by = this.mulg1.forward(b, y);
        Unit axpby = this.addg0.forward(ax, by);
        axpbypc = this.addg1.forward(axpby, c); // a*x + b*y + c
        return axpbypc;
    }
    
    public void backward(double gradient_top) {
        axpbypc.grad = gradient_top;
        this.addg1.backward();
        this.addg0.backward();
        this.mulg1.backward();
        this.mulg0.backward();
    }
}
