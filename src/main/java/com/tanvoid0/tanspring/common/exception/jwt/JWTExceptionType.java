package com.tanvoid0.tanspring.common.exception.jwt;


public enum JWTExceptionType {
  INVALID("Invalid", "Invalid JWT Signature"),
  INVALID_TOKEN("InvalidToken", "Invalid JWT Token"),
  EXPIRED("Expired", "Expired JWT Signature"),
  UNSUPPORTED("Unsupported", "Unsupported JWT token"),
  MISSING("Missing", "JWT String is missing");


  private final String type;
  private final String message;

  JWTExceptionType(String key, String message) {
    this.type = key;
    this.message = message;
  }

  public String getType() {
    return type;
  }

  public String getMessage() {
    return message;
  }
}
