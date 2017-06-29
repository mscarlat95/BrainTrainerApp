package com.scarlat.marius.braintrainer.GameDesign;


import android.util.Log;

import com.scarlat.marius.braintrainer.OperationTypes.Operation;
import com.scarlat.marius.braintrainer.Util.Constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Game {

    private static final String TAG = "Game";
    
    private boolean firstContact;
    private int score, attempts;
    private int range;
    private int correctAnsIndex, availableAnswers;

    private boolean active;
    private Random randomGenerator;
    private ArrayList<Integer> answers;
    private String[] operationTypes = { Constants.ADDITION, Constants.SUBSTRACT,
                                        Constants.MULTIPLICATION, Constants.DIVISION};
    private Operation operation;


    public Game() { firstContact = true; }

    public void initializeGameInfo() {
        active = true;
        score = 0;
        attempts = 0;
        range = 1;

        if (firstContact) {
            availableAnswers = 4;
            answers = new ArrayList<>(availableAnswers);
            randomGenerator = new Random();
            firstContact = false;
        }
    }

    public void generateOperation() {

        int opTypeIndex = randomGenerator.nextInt(4);
        updateRange(operationTypes[opTypeIndex]);

        int a = randomGenerator.nextInt(range) + 1;
        int b = randomGenerator.nextInt(range) + 1;

        if (operationTypes[opTypeIndex].equals(Constants.DIVISION)) {
            if (a < b) {
                int temp = a;
                a = b;
                b = temp;
            }
            a = b * (a / b);
        }

        try {
            operation = OperationFactory.getOperation(operationTypes[opTypeIndex], a, b);
        } catch (Exception e) {
            Log.e(TAG, "generateQuestions: ", e);
        }

        correctAnsIndex = randomGenerator.nextInt(availableAnswers);
        updateAnswers(operation.getResult());

    }

    private void updateRange(String opType) {

        switch (opType) {
            case Constants.ADDITION:
                range = 100;
                break;
            case Constants.SUBSTRACT:
                range = 100;
                break;
            case Constants.DIVISION:
                range = 50;
                break;
            case Constants.MULTIPLICATION:
                range = 20;
        }
    }

    private boolean valuesInRange(int val1, int val2) {
        int delta = 10;

        return (Math.abs ((Math.abs(val1) - Math.abs(val2))) < delta);
    }

    private void updateAnswers(int correctAns) {

        HashSet<Integer> marked = new HashSet<Integer>();

        if (answers.size() != 0) {
            answers.clear();
        }

        marked.add(correctAns);
        for (int i = 0; i < availableAnswers; ++i) {

            if (i == correctAnsIndex) {
                answers.add(correctAns);
            } else {
                int incorrectAns = randomGenerator.nextInt(range * range) + 1;

                while (true) {
                    if (!marked.contains(incorrectAns) && valuesInRange(correctAns, incorrectAns)) {
                        marked.add(incorrectAns);
                        break;
                    } else {
                        incorrectAns = randomGenerator.nextInt(range * range) + 1;
                    }
                }
                answers.add(incorrectAns);
            }
        }
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    public int getScore() {
        return score;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getCorrectAnsIndex() {
        return correctAnsIndex;
    }

    public int getAvailableAnswers() {
        return availableAnswers;
    }

    public boolean isActive() {
        return active;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public Operation getOperation() {
        return operation;
    }

    public void increaseAttempts() {
        ++ attempts;
    }

    public void increaseScore() {
        ++ score;
    }

}
