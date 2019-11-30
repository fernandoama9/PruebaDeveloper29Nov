package com.loyalty.prueba.demo;

import com.loyalty.prueba.demo.abstracts.OperationAbstract;
import com.loyalty.prueba.demo.exception.InvalidExpressionException;
import com.loyalty.prueba.demo.interfaces.IEvaluateExpression;
import com.loyalty.prueba.demo.interfaces.IParseInterface;
import com.loyalty.prueba.demo.operations.*;
import com.loyalty.prueba.demo.pojo.ErrorResponse;
import com.loyalty.prueba.demo.utils.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.Assert;

import java.util.Stack;

@SpringBootTest
class DevAppsApplicationTests {
	private com.loyalty.prueba.demo.interfaces.IParseInterface<Stack<String>> IParseInterface;
	private IEvaluateExpression iEvaluateExpression;
	private Logger log;
	@Autowired
	public DevAppsApplicationTests(IParseInterface<Stack<String>> IParseInterface,IEvaluateExpression iEvaluateExpression) {
		this.IParseInterface = IParseInterface;
		this.log = LoggerFactory.getLogger(this.getClass());
		this.iEvaluateExpression = iEvaluateExpression;
	}
	@Test
	void contextLoads() {
	}

	@Test
	void isANumber() {
		Assert.assertTrue(NumberValidator.isNumber("1"));
	}

	@Test
	void isNotANumber() {
		Assert.assertFalse(NumberValidator.isNumber("="));
	}

	@Test
	void isAOperator() {
		Assert.assertTrue(OperatorValidator.isOperator("+"));
	}

	@Test
	void isNotAOperator() {
		Assert.assertFalse(OperatorValidator.isOperator("1"));
	}

	@Test
	void hasInvalidCharacters(){
		try {
			CharactersValidators.validateCharacter("12345-qw");
		}catch(InvalidExpressionException e){
			Assert.assertEquals(e.getMessage(), "The expresion {12345-qw} is Invalid");
		}
	}

	@Test
	void notHasInvalidCharacter(){
		try {
			CharactersValidators.validateCharacter("12345-12");
		}catch(InvalidExpressionException e){
		}
	}

	@Test
	void notHasParenthesis(){
		try {
			InvalidCharactersValidator.evaluateInvalidCharacters("12345-12");
		}catch(InvalidExpressionException e){
		}
	}
	@Test
	void hasParenthesis(){
		try {
			InvalidCharactersValidator.evaluateInvalidCharacters("(12345-12)");
		}catch(InvalidExpressionException e){
			Assert.assertEquals(e.getMessage(), "The expresion {(12345-12)} is Invalid");
		}
	}

	@Test
	void validate1Precedence(){
		Assert.assertEquals(PrecedenceValidator.prec("/"), 2);
	}

	@Test
	void validate2Precedence(){
		Assert.assertEquals(PrecedenceValidator.prec("-"), 1);
	}

	@Test
	void validateNoPrecedence(){
		Assert.assertEquals(PrecedenceValidator.prec("0"), -1);
	}
	@Test
	void validateStack(){
		Stack<String> pila = IParseInterface.postfixParse("2+2/3.5");
		Stack<String> pilaAux = new Stack<>();
		pilaAux.push("2");
		pilaAux.push("2");
		pilaAux.push("3");
		pilaAux.push(".");
		pilaAux.push("5");
		pilaAux.push("/");
		pilaAux.push("+");
		Assert.assertEquals(pila, pilaAux);
	}

	@Test
	void validateExpEval(){
		try {
			Double result= iEvaluateExpression.evaluateExpression("12.53/4*+");
			Assert.assertTrue( result == 4.333333333333334);
		}catch (Exception e){

		}
	}

	@Test
	void validateInvalidExpEval(){
		try {
			Double result= iEvaluateExpression.evaluateExpression("12.53/4*+");
			Assert.assertFalse( result != 4.333333333333334);
		}catch (Exception e){

		}
	}

	@Test
	void validateStackToString(){
		Stack<String> pilaAux = new Stack<>();
		pilaAux.push("2");
		pilaAux.push("2");
		pilaAux.push("3");
		pilaAux.push(".");
		pilaAux.push("5");
		pilaAux.push("/");
		pilaAux.push("+");
		Assert.assertEquals(StackToString.stackToString(pilaAux), "223.5/+");
	}

	@Test
	void validateFailStackToString(){
		Stack<String> pilaAux = new Stack<>();
		pilaAux.push("2");
		pilaAux.push("2");
		pilaAux.push("3");
		pilaAux.push(".");
		pilaAux.push("5");
		pilaAux.push("/");
		pilaAux.push("+");
		Assert.assertNotEquals(StackToString.stackToString(pilaAux), "223.52/+");
	}

	@Test
	void validateSum(){
		OperationAbstract op = new Suma();
		Assert.assertTrue(op.operation(1.0,2.0) == 3.0);
	}
	@Test
	void validateRest(){
		OperationAbstract op = new Resta();
		Assert.assertTrue(op.operation(1.0,2.0) == -1.0);
	}
	@Test
	void validateMult(){
		OperationAbstract op = new Multiplicacion();
		Assert.assertTrue(op.operation(1.0,2.0) == 2.0);
	}
	@Test
	void validateDiv(){
		OperationAbstract op = new Division();
		Assert.assertTrue(op.operation(1.0,2.0) == 2.0);
	}
	@Test
	void validatePow(){
		OperationAbstract op = new Pow();
		Assert.assertTrue(op.operation(1.0,2.0) == 1.0);
	}

}
