package java.neuralNet;

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
}
