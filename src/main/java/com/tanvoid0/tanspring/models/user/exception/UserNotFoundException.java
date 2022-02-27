package com.tanvoid0.tanspring.models.user.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.tanvoid0.tanspring.core.exception.MapConvertible;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserNotFoundException extends RuntimeException implements MapConvertible {
  private final Map<String, Serializable> map = new HashMap<>();

  public UserNotFoundException(String key, String value) {
    super(String.format("User with %s=%s not found.", key, value));
    map.put(key, value);
  }

  @Override
  public Map<String, Serializable> toMap() {
    return map;
  }
}
