package com.tanvoid0.tanspring.core.exception;

import java.io.Serializable;
import java.util.Map;

public interface MapConvertible extends Serializable {
  Map<String, Serializable> toMap();
}
