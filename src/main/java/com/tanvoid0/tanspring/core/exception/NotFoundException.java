package com.tanvoid0.tanspring.core.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class NotFoundException extends RuntimeException implements MapConvertible {
  protected final Map<String, Serializable> map = new HashMap<>();

  public NotFoundException(String item, String key, String value) {
    super(String.format("%s with %s=%s not found.", item, key, value));
    map.put(key, value);
  }

  @Override
  public Map<String, Serializable> toMap() {
    return map;
  }
}
