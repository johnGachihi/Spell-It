package com.johnwaithaka.angel.exception;

public class UsernameExistsException extends Exception {
    public UsernameExistsException(){
        super();
    }

    public UsernameExistsException(String message){
        super(message);
    }

    public UsernameExistsException(String message, Throwable t){
        super(message, t);
    }
}
