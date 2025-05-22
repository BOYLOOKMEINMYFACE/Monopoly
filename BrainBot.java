package src.main;

import java.util.ArrayList;

public class BrainBot extends BotPlayer {
    private Brain brain;
    private static int botId = 0;
    private static double mutationRate = 0.01; // Mutation rate for genetic algorithm
    public static final double KEEP_PCT = 0.05; // Percentage of bots to keep for next generation

    public BrainBot() {
        super("Original BrainBot #" + botId);
        botId++;
        strategy = "Random Neural Network Brain";
        brain = new Brain();
    }

    public BrainBot(Brain brain) {
        super("Spawned BrainBot #" + botId);
        botId++;
        strategy = "Trained BrainBot";
        this.brain = brain;
    }

    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore) {
        int highestOtherScore = 0;
        for (int score : otherScores) {
            if (score > highestOtherScore) {
                highestOtherScore = score;
            }
        }
        double[] input = { myScore, handScore, winningScore, highestOtherScore };
        boolean result = brain.result(input);
        return result;
    }

    public double[][] getWeightsInputHidden() {
        return brain.getWeightsInputHidden();
    }

    public Brain getBrain() {
        return brain;
    }

    public static BrainBot mate(BrainBot dad, BrainBot mom) {
        Brain dadBrain = dad.getBrain();
        Brain momBrain = mom.getBrain();

        double[][] dadWeightsInputHidden = dadBrain.getWeightsInputHidden();
        double[][] momWeightsInputHidden = momBrain.getWeightsInputHidden();
        double[] dadWeightsHiddenOutput = dadBrain.getWeightsHiddenOutput();
        double[] momWeightsHiddenOutput = momBrain.getWeightsHiddenOutput();

        int inputSize = dadBrain.getInputSize();
        int hiddenSize = dadBrain.getHiddenSize();
        double[][] childWeightsInputHidden = new double[inputSize][hiddenSize];
        double[] childWeightsHiddenOutput = new double[hiddenSize];

        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < hiddenSize; j++) {
                if (Math.random() < 0.5) {
                    childWeightsInputHidden[i][j] = dadWeightsInputHidden[i][j];
                } else {
                    childWeightsInputHidden[i][j] = momWeightsInputHidden[i][j];
                }
                if (Math.random() < mutationRate) { // mutate
                    childWeightsInputHidden[i][j] += Math.random() - 1;
                }
            }
        }

        for (int i = 0; i < hiddenSize; i++) {
            if (Math.random() < 0.5) {
                childWeightsHiddenOutput[i] = dadWeightsHiddenOutput[i];
            } else {
                childWeightsHiddenOutput[i] = momWeightsHiddenOutput[i];
            }
            if (Math.random() < mutationRate) { // mutate
                childWeightsHiddenOutput[i] += Math.random() - 1;
            }
        }

        Brain childBrain = new Brain(childWeightsInputHidden, childWeightsHiddenOutput);
        return new BrainBot(childBrain);
    }
}
