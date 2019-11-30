package com.loyalty.prueba.demo.process;

import com.loyalty.prueba.demo.interfaces.IParseInterface;
import com.loyalty.prueba.demo.utils.NumberValidator;
import com.loyalty.prueba.demo.utils.PrecedenceValidator;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service("parser")
public class Parser implements IParseInterface<Stack<String>> {
    @Override
    public Stack<String> postfixParse(String expr) {
        Stack<String> outputStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        String[] exp = expr.split("");
        for(int i = 0; i<expr.length(); ++i){
            String c = exp[i];
            if(NumberValidator.isNumber(c)){
                outputStack.push(c);
            }else{
                while(!operatorStack.isEmpty() && PrecedenceValidator.prec(c) <= PrecedenceValidator.prec(operatorStack.peek())){
                    outputStack.push(operatorStack.pop());
                }
                operatorStack.push(c);
            }

        }
        while(!operatorStack.isEmpty()){
            outputStack.push(operatorStack.pop());
        }
        return outputStack;
    }
}
