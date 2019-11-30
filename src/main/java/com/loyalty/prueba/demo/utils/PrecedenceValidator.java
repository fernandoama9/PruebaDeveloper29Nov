package com.loyalty.prueba.demo.utils;

public class PrecedenceValidator {
    public static int prec(String operator)
    {
        if(operator.equalsIgnoreCase("+") || operator.equalsIgnoreCase("-")){
            return 1;
        }
        if(operator.equalsIgnoreCase("/") || operator.equalsIgnoreCase("*")){
            return 2;
        }
        if(operator.equalsIgnoreCase("^")){
            return 3;
        }
        return -1;
    }
}
