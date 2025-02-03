package com.javaweb.customException;


public class InvalidParamException extends Exception{
    public InvalidParamException(String message) {
        super(message);
    }
}
