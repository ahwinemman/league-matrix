package com.rukevwe.league.error;

public class InvalidFileException extends RuntimeException{

    public InvalidFileException(String message) {
        super(message);
    }

    public InvalidFileException(String message, Throwable ex) {
        super(message, ex);
    }
}
