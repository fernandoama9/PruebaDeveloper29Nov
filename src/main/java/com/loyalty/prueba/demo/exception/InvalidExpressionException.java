package com.loyalty.prueba.demo.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class InvalidExpressionException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;
    public InvalidExpressionException(String errorMessage) {
        super();
        this.message = errorMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
