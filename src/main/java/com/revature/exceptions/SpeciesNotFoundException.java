package com.revature.exceptions;

public class SpeciesNotFoundException extends RuntimeException {

    public SpeciesNotFoundException() {
        super("Species not found");
    }

    public SpeciesNotFoundException(String message) {
        super(message);
    }

}
