package com.tanvoid0.tanspring.common.exception.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RESTValidationError implements RESTSubError {
  private String object;
  private String field;
  private Object rejectedValue;
  private String message;

  public RESTValidationError(final String object, final String message) {
    this.object = object;
    this.message = message;
  }
}
