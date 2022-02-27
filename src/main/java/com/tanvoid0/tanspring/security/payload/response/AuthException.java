package com.tanvoid0.tanspring.security.payload.response;

import com.tanvoid0.tanspring.core.exception.MapConvertible;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AuthException extends RuntimeException implements MapConvertible {
  protected Map<String, Serializable> map = new HashMap<>();

  public AuthException(String message) {
    super(message);
    this.map.put("message", message);
  }

  @Override
  public final Map<String, Serializable> toMap() {
    return map;
  }
}
