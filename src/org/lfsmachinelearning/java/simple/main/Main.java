package org.lfsmachinelearning.java.simple.main;

import java.util.Random;

import org.lfsmachinelearning.java.simple.gate.AddGate;
import org.lfsmachinelearning.java.simple.gate.MultiplyGate;
import org.lfsmachinelearning.java.simple.gate.SigmoidGate;

/**
 * 
 * @author Timdpr
 * 
 * Based off the Single Neuron example at http://karpathy.github.io/neuralnets/
 * 
 * A single neuron makes the function σ(ax+by+c) = 1, with random numbers
 * between -5 and 5 as initial inputs (σ is the sigmoid function)
 *
 * It is very inefficient and should use arrays, optimisations, an SVM etc...but as a 
 * very simple neural network and a learning tool, it's great!
 * 
 */
public class Main {
    private static Unit a;
    private static Unit b;
    private static Unit c;
    private static Unit x;
    private static Unit y;

    private static Unit ax;
    private static Unit by;
    private static Unit axpby;
    private static Unit axpbypc;
    private static Unit s;

    private static MultiplyGate mulg0;
    private static MultiplyGate mulg1;
    private static AddGate addg0;
    private static AddGate addg1;
    private static SigmoidGate sg0;

    private static Random random = new Random();

    public static void main(String[] args) {
        createUnitsAndGates();
        run();
    }

    public static void run() {     
        System.out.println("Inputs initially:\na: " + a.value + " b: " + b.value + " c: " + c.value + " x: " + x.value + " y: " + y.value + "\n");
        int numberOfIterations = 10000;
        for (int i=0; i<numberOfIterations; i++) {

            forwardNeuron(); // do the forward pass
            System.out.println("Circuit output after " + i + " backprop(s): " + s.value);
            backwardNeuron(); // compute gradients
            modifyInputs(); // 'pull on' inputs using gradients

            if (s.value == 1.0) {
                System.out.println("\nInputs after learning:\na: " + a.value + " b: " + b.value + " c: " + c.value + " x: " + x.value + " y: " + y.value);
                break;
            }
        }
    }

    public static void forwardNeuron() {
        // do the forward pass
        ax = mulg0.forward(a, x); // a*x
        by = mulg1.forward(b, y); // b*y
        axpby = addg0.forward(ax, by); // a*x + b*y
        axpbypc = addg1.forward(axpby, c); // a*x + b*y + c
        s = sg0.forward(axpbypc); // sig(a*x + b*y + c)
    }

    public static void backwardNeuron() {
        // compute gradients
        s.grad = 1.0;
        sg0.backward(); // writes gradient into axpbypc
        addg1.backward(); // writes gradients into axpby and c
        addg0.backward(); // writes gradients into ax and by
        mulg1.backward(); // writes gradients into b and y
        mulg0.backward(); // writes gradients into a and x
    }

    public static void modifyInputs() {
        // 'pull on' inputs using gradients
        double step_size = 0.01;
        a.value += step_size * a.grad;
        b.value += step_size * b.grad;
        c.value += step_size * c.grad;
        x.value += step_size * x.grad;
        y.value += step_size * y.grad;
    }

    public static void createUnitsAndGates() {
        a = new Unit(random.nextInt(11)-5);
        b = new Unit(random.nextInt(11)-5);
        c = new Unit(random.nextInt(11)-5);
        x = new Unit(random.nextInt(11)-5);
        y = new Unit(random.nextInt(11)-5);

        mulg0 = new MultiplyGate();
        mulg1 = new MultiplyGate();
        addg0 = new AddGate();
        addg1 = new AddGate();
        sg0 = new SigmoidGate();
    }
}
