package com.plaza.hotel.exception;

public class NotFoundException extends RuntimeException {

    private final String messageError;

    public NotFoundException(final String messageError) {
        super((messageError));
        this.messageError = messageError;
    }

    public String getMessageError() {
        return messageError;
    }
}
