package com.tanvoid0.tanspring.common.exception;

import java.io.Serializable;
import java.util.Map;

public interface MapConvertible extends Serializable {
  Map<String, Serializable> toMap();
}
