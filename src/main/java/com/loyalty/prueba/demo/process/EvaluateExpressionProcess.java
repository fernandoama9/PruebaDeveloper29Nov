package com.loyalty.prueba.demo.process;

import com.loyalty.prueba.demo.abstracts.OperationAbstract;
import com.loyalty.prueba.demo.interfaces.IEvaluateExpression;
import com.loyalty.prueba.demo.operations.*;
import com.loyalty.prueba.demo.utils.NumberValidator;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service("ExpresionEvaluator")
public class EvaluateExpressionProcess implements IEvaluateExpression {
    @Override
    public Double evaluateExpression(String postFixExp) throws Exception {
        Stack<Double> stack=new Stack<>();
        String[] exp = postFixExp.split("");
        String aux = "";
        Boolean flag = false;
        for(int i=0;i<postFixExp.length();i++){
            String c=exp[i];
            if(NumberValidator.isNumber(c)){
                if(c.equalsIgnoreCase(".")){
                    Double top = stack.pop();
                    aux = top.intValue() + c;
                    flag = true;
                    continue;
                }else{
                    if(flag){
                        c = aux + c;
                        flag = false;
                        aux = "";
                    }
                    stack.push(Double.parseDouble(c));
                }
            }else{
                Double arg1 = stack.pop();
                Double arg2 = stack.pop();
                OperationAbstract operacion;
                switch(c){
                    case "+":
                        operacion = new Suma();
                        stack.push(operacion.operation(arg1,arg2));
                        break;
                    case "-":
                        operacion = new Resta();
                        stack.push(operacion.operation(arg2, arg1));
                        break;
                    case "/":
                        operacion = new Division();
                        if(arg1 == 0){
                            throw new Exception();
                        }
                        stack.push(operacion.operation(arg1, arg2));
                        break;
                    case "*":
                        operacion = new Multiplicacion();
                        stack.push(operacion.operation(arg2, arg1));
                        break;
                    case "^":
                    operacion = new Pow();
                    stack.push(operacion.operation(arg2, arg1));
                    break;
                }
            }
        }
        return stack.pop();
    }
}
