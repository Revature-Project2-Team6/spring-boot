package com.revature.exceptions;

/**
 * Exception class that is thrown when the requested user information was not
 * found in the database.
 *
 * @author Teejae Bautista
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
