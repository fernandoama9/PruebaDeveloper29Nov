package com.loyalty.prueba.demo.utils;

import java.util.Stack;

public class StackToString {
    public static String stackToString(Stack<String> stack){
        String resp = "";
        for (int i = 0; i<stack.size(); i++){
            resp += stack.elementAt(i);
        }
        return resp;
    }
}
