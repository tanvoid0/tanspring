package com.tanvoid0.tanspring.common.exception;

import com.tanvoid0.tanspring.common.vo.ErrorDetailsVO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  // handle specific exceptions
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetailsVO> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
    ErrorDetailsVO errorDetailsVO = new ErrorDetailsVO(new Date(), exception.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetailsVO, HttpStatus.NOT_FOUND);
  }

  // global exceptions
  @ExceptionHandler(BlogAPIException.class)
  public ResponseEntity<ErrorDetailsVO> handleBlogAPIException(BlogAPIException exception, WebRequest webRequest) {
    ErrorDetailsVO errorDetailsVO = new ErrorDetailsVO(new Date(), exception.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetailsVO, HttpStatus.BAD_REQUEST);
  }

  // global exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetailsVO> handleGlobalException(Exception exception, WebRequest webRequest) {
    ErrorDetailsVO errorDetailsVO = new ErrorDetailsVO(new Date(), exception.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetailsVO, HttpStatus.INTERNAL_SERVER_ERROR);
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
}
