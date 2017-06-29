package com.scarlat.marius.braintrainer.OperationTypes;

public abstract class Operation {

    int a, b;
    private String symbol;

    public Operation (int a, int b, String symbol) {
        this.a = a;
        this.b = b;
        this.symbol = symbol;
    }

    public int getA() { return a; }
    public int getB() { return b; }
    public String getSymbol() { return symbol; }

    public abstract int getResult();

}