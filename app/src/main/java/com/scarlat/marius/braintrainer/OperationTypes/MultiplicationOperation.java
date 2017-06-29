package com.scarlat.marius.braintrainer.OperationTypes;


import com.scarlat.marius.braintrainer.Util.Constants;

public class MultiplicationOperation extends Operation {

    public MultiplicationOperation(int a, int b) {
        super(a, b, Constants.MULTIPLICATION_SYMBOL);
    }

    @Override
    public int getResult() {
        return a * b;
    }
}

