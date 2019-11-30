package com.loyalty.prueba.demo.utils;

public class OperatorValidator {
    public static Boolean isOperator(String input){
        if(input == "+" || input == "-" || input == "/" || input == "*") {
            return true;
        }
        return false;
    }
}
