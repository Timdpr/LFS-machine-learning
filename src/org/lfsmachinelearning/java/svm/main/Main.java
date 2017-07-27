package org.lfsmachinelearning.java.svm.main;

import java.util.ArrayList;

import org.lfsmachinelearning.java.simple.main.Unit;

public class Main {
    private static ArrayList<Pair> data = new ArrayList<Pair>();
    private static ArrayList<Integer> labels = new ArrayList<Integer>();
    private static SVM svm;

    public static void main(String[] args) {
        data.add(new Pair(1.2, 0.7)); labels.add(1);
        data.add(new Pair(-0.3, -0.5)); labels.add(-1);
        data.add(new Pair(3.0, 0.1)); labels.add(1);
        data.add(new Pair(-0.1, -1.0)); labels.add(-1);
        data.add(new Pair(-1.0, 1.1)); labels.add(-1);
        data.add(new Pair(2.1, -3.0)); labels.add(1);
        svm = new SVM();

        for (int iter=0; iter<401; iter++) {
            int i = (int) Math.floor(Math.random() * data.size());
            Unit x = new Unit(data.get(i).getX());
            Unit y = new Unit(data.get(i).getY());
            int label = labels.get(i);

            svm.learnFrom(x, y, label);

            if (evalTrainingAccuracy() == 1.0) {
                System.out.println("Accuracy is 1.0 at iter " + iter + "!");
                break;
            }

            if (iter % 25 == 0) { // every 25 iterations... 
                System.out.println("Training accuracy at iter " + iter + ": " + evalTrainingAccuracy());
            }
        }
    }

    public static double evalTrainingAccuracy() {
        double numCorrect = 0.0;
        for (int i=0; i<data.size(); i++) {
            Unit x = new Unit(data.get(i).getX());
            Unit y = new Unit(data.get(i).getY());

            int trueLabel = labels.get(i);
            int predictedLabel = svm.forward(x, y).value > 0 ? 1 : -1;

            if (predictedLabel == trueLabel) {
                numCorrect++;
            }
        }
        return numCorrect / data.size();
    }
}
