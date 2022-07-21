package com.revature.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        // no args contructor autoamtically says this err
        super("Invalid username/password.");

    }

    public AuthenticationException(String message) {
        super(message);
    }

}
