package com.loyalty.prueba.demo.operations;

import com.loyalty.prueba.demo.abstracts.OperationAbstract;

public class Pow extends OperationAbstract {
    @Override
    public Double operation(Double arg1, Double arg2) {
        return Math.pow(arg1,arg2);
    }
}
