package com.tanvoid0.tanspring.common.exception;

import org.springframework.http.HttpStatus;

public class AuthException extends RuntimeException {

  private final HttpStatus status;
  private String message;

  public AuthException(HttpStatus status, String message) {
    super(message);
    this.message = message;
    this.status = status;
  }

  public AuthException(String message) {
    this(HttpStatus.BAD_REQUEST, message);
  }
}
