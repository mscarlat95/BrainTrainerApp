package com.scarlat.marius.braintrainer.OperationTypes;


import com.scarlat.marius.braintrainer.Util.Constants;

public class DivisionOperation extends Operation {

    public DivisionOperation(int a, int b) {
        super(a, b, Constants.DIVISION_SYMBOL);
    }

    @Override
    public int getResult() {
        return a / b;
    }
}