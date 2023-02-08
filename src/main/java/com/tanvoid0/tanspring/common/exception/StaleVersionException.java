package com.tanvoid0.tanspring.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StaleVersionException extends RuntimeException {

  public StaleVersionException() {
    super("Requested version of object does not match with existing object in the database");
  }

}
