package com.loyalty.prueba.demo.utils;

import com.loyalty.prueba.demo.exception.InvalidExpressionException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class InvalidCharactersValidator {
    public static void evaluateInvalidCharacters(String expression) throws InvalidExpressionException {
        if(expression.isEmpty() || expression.equalsIgnoreCase("")|| expression.contains("(") || expression.contains(")") || expression.charAt(0) == '-'){
            throw new InvalidExpressionException("The expresion {" + expression + "} is Invalid");
        }
        String aux = "+-*/";
        String[] operators = aux.split("");
        String[] opAux = aux.split("");
        for(int i=0; i<operators.length;i++){
            for(int j=0; j<opAux.length;j++) {
                if(expression.contains(operators[i] + opAux[j])){
                    throw new InvalidExpressionException("The expresion {" + expression + "} is Invalid");
                }
            }
        }

    }
}
