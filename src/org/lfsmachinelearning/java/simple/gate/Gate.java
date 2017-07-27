package org.lfsmachinelearning.java.simple.gate;

public interface Gate {    
    /**
     * Calculates local derivative with respect to its input, then * with the
     * 'top pull' gradient (chain rule!) from the forward pass' output unit (uTop.grad)
     */
    public void backward();
}
