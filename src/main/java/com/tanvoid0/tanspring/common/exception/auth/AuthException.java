package com.tanvoid0.tanspring.common.exception.auth;

import com.tanvoid0.tanspring.common.exception.MapConvertible;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AuthException extends RuntimeException implements MapConvertible {

  private final HttpStatus status;
  private String message;

  private final Map<String, Serializable> properties = new HashMap<>();

  public AuthException(HttpStatus status, String message) {
    super(message);
    this.message = message;
    this.status = status;
    this.properties.put("message", message);
  }

  public AuthException(String message) {
    this(HttpStatus.BAD_REQUEST, message);
  }

  @Override
  public Map<String, Serializable> toMap() {
    return properties;
  }
}
