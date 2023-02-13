package com.tanvoid0.tanspring.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tanvoid0.tanspring.common.exception.util.RESTSubError;
import com.tanvoid0.tanspring.common.exception.util.RESTValidationError;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;

@Getter
@Setter
public class ExceptionDetailsVO {
  private HttpStatus status;
  private int statusCode;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;
  private String message;
  private String debugMessage;
  private List<RESTSubError> subErrors;
  private Map<String, String> validationErrors;
  private String type;
  private Map<String, Serializable> additionalAttributes = new HashMap<>();
  private String name;

  private Map<String, Serializable> properties = new HashMap<>();

  ExceptionDetailsVO(final HttpStatus status) {
    this(status, null, null);
  }

  ExceptionDetailsVO(final HttpStatus status, final Throwable ex) {
    this(status, "Unexpected error", ex);
  }

  ExceptionDetailsVO(final HttpStatus status, final String message, final Throwable ex) {
    this(status, message, ex, (ex instanceof MapConvertible) ? ((MapConvertible) ex).toMap() : null);
  }

  ExceptionDetailsVO(final HttpStatus status, final String message, final Throwable ex, final Map<String, Serializable> properties) {
    timestamp = LocalDateTime.now();
    this.status = status;
    this.statusCode = status.value();
    this.message = message;

    if (ex != null) {
      this.debugMessage = ex.getLocalizedMessage();
      this.type = ex.getClass().getSimpleName();
    } else {
      this.debugMessage = status.getReasonPhrase();
      this.type = status.name();
    }

    if (null != additionalAttributes) {
      this.additionalAttributes.putAll(additionalAttributes);
    }
  }

  private void addSubError(final RESTSubError subError) {
    if (subErrors == null) {
      subErrors = new ArrayList<>();
    }
    subErrors.add(subError);
  }

  private void addValidationError(final RESTValidationError contraintError) {
    if (null == validationErrors) {
      validationErrors = new HashMap<>();
    }
    validationErrors.put(contraintError.getField(), contraintError.getMessage());
  }

  private void addValidationError(final String object, final String field, final Object rejectedValue, final String message) {
    addValidationError(new RESTValidationError(object, field, rejectedValue, message));
  }

  private void addValidationError(final String object, final String message) {
    addSubError(new RESTValidationError(object, message));
  }

  private void addValidationError(final FieldError fieldError) {
    this.addValidationError(
        fieldError.getObjectName(),
        fieldError.getField(),
        fieldError.getRejectedValue(),
        fieldError.getDefaultMessage());
  }

  private void addValidationError(final ObjectError objectError) {
    this.addValidationError(
        objectError.getObjectName(),
        objectError.getDefaultMessage());
  }

  final void addValidationError(final List<ObjectError> globalErrors) {
    globalErrors.forEach(this::addValidationError);
  }

  /**
   * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
   *
   * @param cv the ConstraintViolation
   */
  private void addValidationError(final ConstraintViolation<?> cv) {
    this.addValidationError(
        cv.getRootBeanClass().getSimpleName(),
        getLeafPropertyName(cv.getPropertyPath()),
        cv.getInvalidValue(),
        cv.getMessage());
  }

  private static String getLeafPropertyName(final Path propertyPath) {
    final Iterator<Path.Node> it = propertyPath.iterator();
    Path.Node node = null;

    while (it.hasNext()) {
      node = it.next();
    }

    return (null != node) ? node.toString() : null;
  }

  public final void addValidationErrors(final List<FieldError> fieldErrors) {
    fieldErrors.forEach(this::addValidationError);
  }

  final void addValidationErrors(final Set<ConstraintViolation<?>> constraintViolations) {
    constraintViolations.forEach(this::addValidationError);
  }


//  public ExceptionDetailsVO(final String message, final WebRequest webRequest) {
//    this(new Date(), message, webRequest.getDescription(false));
//  }
//
//  public ExceptionDetailsVO(final String message, final String name, final WebRequest request) {
//    this.message = message;
//    this.timestamp = new Date();
//    this.details = request.getDescription(false);
//    this.name = name;
//  }
//
//  public ExceptionDetailsVO(Date timestamp, String message, String details) {
//    this.timestamp = timestamp;
//    this.message = message;
//    this.details = details;
//  }

//  @Override
//  public Map<String, Serializable> toMap() {
//    return this.properties;
//  }

  //  public ExceptionDetailsVO(String message, String details) {
//    this(new Date(), message, details);
//  }

//  public ResponseEntity getResponse() {
//    return new ResponseEntity<>(this, HttpStatus.BAD_REQUEST);
//  }
}
