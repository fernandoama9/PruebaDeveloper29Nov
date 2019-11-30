package com.loyalty.prueba.demo.utils;

import com.loyalty.prueba.demo.exception.InvalidExpressionException;

public class CharactersValidators {
    public static void validateCharacter(String expression) throws InvalidExpressionException{
        for (int i =0; i<expression.length();i++) {
            if (Character.isAlphabetic(expression.charAt(i))) {
                throw new InvalidExpressionException("The expresion {" + expression + "} is Invalid");
            }
        }

    }
}
