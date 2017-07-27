package org.lfsmachinelearning.java.svm.main;

import org.lfsmachinelearning.java.simple.gate.AddGate;
import org.lfsmachinelearning.java.simple.gate.MultiplyGate;
import org.lfsmachinelearning.java.simple.main.Unit;

public class Circuit {
    public MultiplyGate mulg0 = new MultiplyGate();
    public MultiplyGate mulg1 = new MultiplyGate();
    public AddGate addg0 = new AddGate();
    public AddGate addg1 = new AddGate();
    
    public Unit ax;
    public Unit by;
    public Unit axpby;
    public Unit axpbypc;
    
    public Unit forward(Unit x, Unit y, Unit a, Unit b, Unit c) {
        ax = this.mulg0.forward(a, x); // a*x
        by = this.mulg1.forward(b, y); // b*y
        axpby = this.addg0.forward(ax, by); // a*x + b*y
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
