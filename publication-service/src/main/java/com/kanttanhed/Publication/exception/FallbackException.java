package com.kanttanhed.Publication.exception;

public class FallbackException extends RuntimeException{

    public FallbackException(Throwable cause) {
        super("We are currently unavailable, please try again later.", cause);
    }
}
