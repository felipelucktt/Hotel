package com.plaza.hotel.exception;

public class BadRequestException extends RuntimeException {

  private final String messageError;

  public BadRequestException(final String messageError) {
    super((messageError));
    this.messageError = messageError;
  }

  public String getMessageError() {
    return messageError;
  }
}
