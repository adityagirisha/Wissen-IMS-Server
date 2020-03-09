package com.wissen.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductExistsExeception extends RuntimeException {

    ProductExistsExeception(){
        super("Product exists!");
    }
}
