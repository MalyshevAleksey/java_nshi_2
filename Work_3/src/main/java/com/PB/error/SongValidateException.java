package com.PB.error;

public class SongValidateException extends RuntimeException{
    public SongValidateException(String message) {
        super(message);
    }
}
