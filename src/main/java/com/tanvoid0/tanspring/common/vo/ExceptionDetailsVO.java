package com.tanvoid0.tanspring.common.vo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

public class ExceptionDetailsVO {
  private Date timestamp;
  private String message;
  private String details;

  public ExceptionDetailsVO(final String message, final WebRequest webRequest) {
    this(new Date(), message, webRequest.getDescription(false));
  }

  public ExceptionDetailsVO(Date timestamp, String message, String details) {
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }

  public ExceptionDetailsVO(String message, String details) {
    this(new Date(), message, details);
  }


  public Date getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }

  public ResponseEntity getResponse() {
    return new ResponseEntity<>(this, HttpStatus.BAD_REQUEST);
  }
}
