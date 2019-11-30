package com.loyalty.prueba.demo.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.prueba.demo.exception.InvalidExpressionException;
import com.loyalty.prueba.demo.interfaces.IEvaluateExpression;
import com.loyalty.prueba.demo.interfaces.IParseInterface;
import com.loyalty.prueba.demo.interfaces.IProcess;
import com.loyalty.prueba.demo.pojo.ErrorResponse;
import com.loyalty.prueba.demo.pojo.ExpRequest;
import com.loyalty.prueba.demo.pojo.ExpResponse;
import com.loyalty.prueba.demo.utils.CharactersValidators;
import com.loyalty.prueba.demo.utils.InvalidCharactersValidator;
import com.loyalty.prueba.demo.utils.StackToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service("EvaluateProcess")
public class EvaluateProcess implements IProcess<ResponseEntity<?>, ExpRequest> {
    private IParseInterface<Stack<String>> IParseInterface;
    private IEvaluateExpression iEvaluateExpression;
    private Logger log;
    @Autowired
    public EvaluateProcess(IParseInterface<Stack<String>> IParseInterface,IEvaluateExpression iEvaluateExpression) {
        this.IParseInterface = IParseInterface;
        this.log = LoggerFactory.getLogger(this.getClass());
        this.iEvaluateExpression = iEvaluateExpression;
    }

    @Override
    public ResponseEntity<?> process(ExpRequest... params) {
        try{
            log.info("Recieved request: {}", new ObjectMapper().writeValueAsString(params[0]));
            CharactersValidators.validateCharacter(params[0].getExp());
            InvalidCharactersValidator.evaluateInvalidCharacters(params[0].getExp());
            Stack<String> output = IParseInterface.postfixParse(params[0].getExp());
            String resp = StackToString.stackToString(output);
            Double respuesta = iEvaluateExpression.evaluateExpression(resp);
            log.info(resp);
            log.info(respuesta.toString());
            return new ResponseEntity<>(new ExpResponse.Builder(params[0].getExp()).withPostfix(resp).withResult(respuesta).build(),HttpStatus.OK);
        }catch(InvalidExpressionException e){
            return new ResponseEntity<>(new ErrorResponse.Builder(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(new ErrorResponse.Builder("Some Error ocurred while processing the expression").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
