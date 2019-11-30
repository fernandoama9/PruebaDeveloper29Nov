package com.loyalty.prueba.demo.operations;

import com.loyalty.prueba.demo.abstracts.OperationAbstract;

public class Suma extends OperationAbstract {
    @Override
    public Double operation(Double arg1, Double arg2) {
        return arg1 + arg2;
    }
}
