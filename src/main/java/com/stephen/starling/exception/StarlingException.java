package com.stephen.starling.exception;

public class StarlingException extends Exception {

    private static final long serialVersionUID = 1L;

	public StarlingException(String message) {
        super(message);
    }

    public StarlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarlingException(Throwable cause) {
        super(cause);
    }
    
}