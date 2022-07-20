package com.revature.exceptions;

public class CharacterNotFoundException extends RuntimeException {

    public CharacterNotFoundException() {
        super("Character not found");
    }

    public CharacterNotFoundException(String message) {
        super(message);
    }

}
