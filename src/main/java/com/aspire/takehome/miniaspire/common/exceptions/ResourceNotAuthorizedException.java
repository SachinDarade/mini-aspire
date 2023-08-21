package com.aspire.takehome.miniaspire.common.exceptions;

public class ResourceNotAuthorizedException extends RuntimeException {
    public ResourceNotAuthorizedException(String message) {
        super(message);
    }
}
