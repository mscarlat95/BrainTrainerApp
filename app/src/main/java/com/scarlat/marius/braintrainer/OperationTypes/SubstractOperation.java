package com.scarlat.marius.braintrainer.OperationTypes;


import com.scarlat.marius.braintrainer.Util.Constants;

public class SubstractOperation extends Operation {

    public SubstractOperation(int a, int b) {
        super(a, b, Constants.SUBSTRACT_SYMBOL);
    }

    @Override
    public int getResult() {
        return a - b;
    }
}