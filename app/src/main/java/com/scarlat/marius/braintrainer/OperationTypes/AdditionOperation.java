package com.scarlat.marius.braintrainer.OperationTypes;


import com.scarlat.marius.braintrainer.Util.Constants;

public class AdditionOperation extends Operation {

    public AdditionOperation(int a, int b) {
        super(a, b, Constants.ADDITION_SYMBOL);
    }

    @Override
    public int getResult() {
        return a + b;
    }
}