package com.scarlat.marius.braintrainer.GameDesign;

import com.scarlat.marius.braintrainer.OperationTypes.AdditionOperation;
import com.scarlat.marius.braintrainer.OperationTypes.DivisionOperation;
import com.scarlat.marius.braintrainer.OperationTypes.MultiplicationOperation;
import com.scarlat.marius.braintrainer.OperationTypes.Operation;
import com.scarlat.marius.braintrainer.OperationTypes.SubstractOperation;
import com.scarlat.marius.braintrainer.Util.Constants;

public class OperationFactory {

     static Operation getOperation (String opType, int a, int b) throws Exception {
        switch (opType) {
            case Constants.ADDITION:
                return new AdditionOperation(a, b);

            case Constants.SUBSTRACT:
                return new SubstractOperation(a, b);

            case Constants.MULTIPLICATION:
                return new MultiplicationOperation(a, b);

            case Constants.DIVISION:
                return new DivisionOperation(a, b);

            default:
                throw new Exception(Constants.INVALID_OPERATION);
        }
    }
}
