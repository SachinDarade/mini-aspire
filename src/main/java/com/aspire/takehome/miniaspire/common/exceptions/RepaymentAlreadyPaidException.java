package com.aspire.takehome.miniaspire.common.exceptions;

public class RepaymentAlreadyPaidException extends RuntimeException {
    public RepaymentAlreadyPaidException(String message) {
        super(message);
    }
}
