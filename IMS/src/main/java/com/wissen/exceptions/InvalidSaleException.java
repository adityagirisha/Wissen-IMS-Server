package com.wissen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidSaleException extends  RuntimeException{


    public InvalidSaleException() {
        super("Sale does not exist");
    }
}

