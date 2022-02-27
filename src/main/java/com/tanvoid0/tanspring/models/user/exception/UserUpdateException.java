package com.tanvoid0.tanspring.models.user.exception;

import com.tanvoid0.tanspring.core.exception.MapConvertible;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserUpdateException extends RuntimeException implements MapConvertible {
  private final Map<String, Serializable> map = new HashMap<String, Serializable>();

  public UserUpdateException(String error) {
    super(String.format("User update failed with exception: %s", error));
    this.map.put("error", error);
  }

  @Override
  public Map<String, Serializable> toMap() {
    return this.map;
  }
}
