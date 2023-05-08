package com.hospital.Api.exception;

import lombok.Data;

@Data
public class GenericException extends RuntimeException{
    private int statusCode;
    private String errorMessage;
    public GenericException(int statusCode,String errorMessage){
        super(errorMessage);
        this.statusCode=statusCode;
        this.errorMessage=errorMessage;
    }
}
