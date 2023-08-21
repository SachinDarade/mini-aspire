package com.aspire.takehome.miniaspire.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
