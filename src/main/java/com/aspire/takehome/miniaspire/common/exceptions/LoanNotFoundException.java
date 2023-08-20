package com.aspire.takehome.miniaspire.common.exceptions;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String message) {
        super(message);
    }
}

