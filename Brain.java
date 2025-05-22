package src.main;

import java.util.Random;

public class Brain {
    private int[] layers = { 4, 5, 1 }; // Number of neurons in each layer
    private double[][] weightsInputHidden; // Weights between input and hidden layer
    private double[] weightsHiddenOutput; // Weights between hidden and output layer
    private int inputSize, hiddenSize;

    public Brain() {
        this.inputSize = layers[0];
        this.hiddenSize = layers[1];

        // Initialize weights and biases
        weightsInputHidden = new double[inputSize][hiddenSize];
        weightsHiddenOutput = new double[hiddenSize];

        initializeWeights();
    }

    public Brain(double[][] weightsInputHidden, double[] weightsHiddenOutput) {
        this.weightsInputHidden = weightsInputHidden;
        this.weightsHiddenOutput = weightsHiddenOutput;

        this.inputSize = layers[0];
        this.hiddenSize = layers[1];
    }

    // Randomly initialize weights
    private void initializeWeights() {
        Random rand = new Random();
        for (int i = 0; i < inputSize; i++)
            for (int j = 0; j < hiddenSize; j++)
                weightsInputHidden[i][j] = rand.nextGaussian(); // Normal distribution

        for (int i = 0; i < hiddenSize; i++)
            weightsHiddenOutput[i] = rand.nextGaussian();
    }

    // Forward propagation
    public boolean result(double[] input) {
        // Compute hidden layer activations
        double[] hiddenLayer = new double[hiddenSize];

        // for (int i = 0; i < input.length; i++) {
        //     input[i] = (double) input[i] / input[2]; // Normalize input, not much effect
        // }

        for (int i = 0; i < hiddenSize; i++) {
            for (int j = 0; j < inputSize; j++) {
                hiddenLayer[i] += input[j] * weightsInputHidden[j][i]; // Weighted sum
            }
            hiddenLayer[i] = Math.tanh(hiddenLayer[i]); // Apply activation function
        }

        // Compute output layer activations
        double outputLayer = 0;
        for (int i = 0; i < hiddenSize; i++) {
            outputLayer += hiddenLayer[i] * weightsHiddenOutput[i];
        }
        outputLayer = Math.tanh(outputLayer); // Apply activation function

        return outputLayer > 0; // Return final prediction
    }

    // Activation function (Sigmoid)
    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public double[][] getWeightsInputHidden() {
        return weightsInputHidden;
    }

    public double[] getWeightsHiddenOutput() {
        return weightsHiddenOutput;
    }

    public int getInputSize() {
        return inputSize;
    }

    public int getHiddenSize() {
        return hiddenSize;
    }
}
