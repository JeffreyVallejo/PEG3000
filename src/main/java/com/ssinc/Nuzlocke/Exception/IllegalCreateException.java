package com.ssinc.Nuzlocke.Exception;

public class IllegalCreateException extends RuntimeException{

    public IllegalCreateException(String errorMessage) {
        super(errorMessage);
    }
}
