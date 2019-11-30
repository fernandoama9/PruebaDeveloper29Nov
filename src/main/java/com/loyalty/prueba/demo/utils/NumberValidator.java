package com.loyalty.prueba.demo.utils;

public class NumberValidator {
    public static Boolean isNumber(String input){
        if (input.matches("[0-9]")) {
            return true;
        }
        if(input.equalsIgnoreCase(".")){
            return true;
        }
        return false;
    }
}
