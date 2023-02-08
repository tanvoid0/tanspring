package com.tanvoid0.tanspring.common.exception.jwt;

public class JWTException extends RuntimeException {
  private JWTExceptionType type;

  public JWTException(final JWTExceptionType type) {
    super(type.getMessage());
    this.type = type;
  }
}

