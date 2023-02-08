package com.tanvoid0.tanspring.common.exception;

import com.tanvoid0.tanspring.common.exception.auth.AuthException;
import com.tanvoid0.tanspring.common.exception.jwt.JWTException;
import com.tanvoid0.tanspring.common.vo.ExceptionDetailsVO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  // handle specific exceptions
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ExceptionDetailsVO> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
    ExceptionDetailsVO exceptionDetailsVO = new ExceptionDetailsVO(new Date(), exception.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(exceptionDetailsVO, HttpStatus.NOT_FOUND);
  }


  // global exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDetailsVO> handleGlobalException(Exception exception, WebRequest webRequest) {
    ExceptionDetailsVO exceptionDetailsVO = new ExceptionDetailsVO(new Date(), exception.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(exceptionDetailsVO, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Validation Exceptions
  @ExceptionHandler(StaleVersionException.class)
  public ResponseEntity<ExceptionDetailsVO> handleStaleVersionException(final StaleVersionException ex, final WebRequest webRequest) {
    final ExceptionDetailsVO exDetailsVO = new ExceptionDetailsVO(new Date(), ex.getMessage(), webRequest.getDescription(false));

    return new ResponseEntity<>(exDetailsVO, HttpStatus.BAD_REQUEST);
  }

  /// SQL Validation Exceptions
  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public ResponseEntity<ExceptionDetailsVO> handleConstraintViolationException(final SQLIntegrityConstraintViolationException ex, final WebRequest webRequest) {
    final ExceptionDetailsVO exceptionDetailsVO = new ExceptionDetailsVO(ex.getMessage(), webRequest);
    return new ResponseEntity<>(exceptionDetailsVO, HttpStatus.BAD_REQUEST);
  }

  // Auth exceptions
  @ExceptionHandler(AuthException.class)
  public ResponseEntity<ExceptionDetailsVO> handleAuthException(final AuthException exception, WebRequest webRequest) {
    final ExceptionDetailsVO exceptionDetailsVO = new ExceptionDetailsVO(new Date(), exception.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(exceptionDetailsVO, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(JWTException.class)
  public ResponseEntity<ExceptionDetailsVO> handleJWTException(final JWTException ex, final WebRequest webRequest) {
    final ExceptionDetailsVO exceptionDetailsVO = new ExceptionDetailsVO(new Date(), ex.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(exceptionDetailsVO, HttpStatus.BAD_REQUEST);
//    return new ExceptionDetailsVO(ex.getMessage(), webRequest).getResponse();
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

// 2nd approach
//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest) {
//    Map<String, String> errors = new HashMap<>();
//    exception.getBindingResult().getAllErrors().forEach((error) -> {
//      String fieldName = ((FieldError)error).getField();
//      String message = error.getDefaultMessage();
//      errors.put(fieldName, message);
//    });
//    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//  }


  // global exceptions
//  @ExceptionHandler(BlogAPIException.class)
//  public ResponseEntity<ErrorDetailsVO> handleBlogAPIException(BlogAPIException exception, WebRequest webRequest) {
//    ErrorDetailsVO errorDetailsVO = new ErrorDetailsVO(new Date(), exception.getMessage(), webRequest.getDescription(false));
//    return new ResponseEntity<>(errorDetailsVO, HttpStatus.BAD_REQUEST);
//  }
}
