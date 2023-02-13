package com.tanvoid0.tanspring.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanvoid0.tanspring.common.exception.auth.AuthException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public final class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  private static final String MALFORMED_REQUEST = "Malformed request";

  private final ObjectMapper objectMapper;

//  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
//      final HttpRequestMethodNotSupportedException ex,
//      final HttpHeaders headers,
//      final HttpStatus status,
//      final WebRequest request
//  ) {
//    final Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
//    if (!CollectionUtils.isEmpty(supportedMethods)) {
//      headers.setAllow(supportedMethods);
//    }
//    return buildResponseEntity(new ExceptionDetailsVO(status, String.format("Method %s not allowed", ex.getMethod()), ex), headers);
//  }
//
//  @ExceptionHandler(MissingServletRequestParameterException.class)
//  protected ResponseEntity<Object> handleMissingServletRequestParameter(
//      final MissingServletRequestParameterException ex,
//      final HttpHeaders headers,
//      final HttpStatus status,
//      final WebRequest request
//  ) {
//    final String error = ex.getParameterName() + " parameter is missing";
//    return buildResponseEntity(new ExceptionDetailsVO(HttpStatus.BAD_REQUEST, error, ex));
//  }
//
//  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
//      final HttpMediaTypeNotSupportedException ex,
//      final HttpHeaders headers,
//      final HttpStatus status,
//      final WebRequest request
//  ) {
//    final StringBuilder builder = new StringBuilder();
//    builder.append(ex.getContentType());
//    builder.append(" media type is not supported. Supported media types are ");
//    builder.append(ex.getSupportedMediaTypes().stream().map(MediaType::toString).collect(Collectors.joining(", ")));
//    return buildResponseEntity(new ExceptionDetailsVO(status, builder.toString(), ex));
//  }
//
//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  private ResponseEntity<Object> handleMethodArgumentNotValid(
//      final MethodArgumentNotValidException ex,
//      final HttpHeaders headers,
//      final HttpStatus status,
//      final WebRequest request) {
//    final ExceptionDetailsVO restError = new ExceptionDetailsVO(status, "Validation error", ex);
//    restError.addValidationErrors(ex.getBindingResult().getFieldErrors());
//    restError.addValidationError(ex.getBindingResult().getGlobalErrors());
//    return buildResponseEntity(restError);
//  }
//
//  @ExceptionHandler(ConstraintViolationException.class)
//  protected ResponseEntity<Object> handleConstraintViolation(
//      final ConstraintViolationException ex) {
//    final ExceptionDetailsVO restError = new ExceptionDetailsVO(HttpStatus.BAD_REQUEST, "Validation error", ex);
//    restError.addValidationErrors(ex.getConstraintViolations());
//    return buildResponseEntity(restError);
//  }
//
//  @ExceptionHandler(HttpMessageNotReadableException.class)
//  protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
//    return buildResponseEntity(new ExceptionDetailsVO(status, "Malformed JSON request", ex));
//  }
//
//  @ExceptionHandler(HttpMessageNotWritableException.class)
//  protected ResponseEntity<Object> handleHttpMessageNotWritable(final HttpMessageNotWritableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
//    return buildResponseEntity(new ExceptionDetailsVO(status, "Error writing JSON output", ex));
//  }

  //
  // handle specific exceptions
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
    ExceptionDetailsVO exceptionDetailsVO = new ExceptionDetailsVO(HttpStatus.NOT_FOUND, exception);
    return buildResponseEntity(exceptionDetailsVO);
  }

  //
//
//  // global exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest webRequest) {
    ExceptionDetailsVO exceptionDetailsVO = new ExceptionDetailsVO(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    return buildResponseEntity(exceptionDetailsVO);
  }

  //
//  // Validation Exceptions
//  @ExceptionHandler(StaleVersionException.class)
//  public ResponseEntity<ExceptionDetailsVO> handleStaleVersionException(final StaleVersionException exception, final WebRequest webRequest) {
//    final ExceptionDetailsVO exDetailsVO = new ExceptionDetailsVO(exception.getMessage(), exception.getClass().getSimpleName(), webRequest);
//    return new ResponseEntity<>(exDetailsVO, HttpStatus.BAD_REQUEST);
//  }
//
//  /// SQL Validation Exceptions
//  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//  public ResponseEntity<ExceptionDetailsVO> handleConstraintViolationException(final SQLIntegrityConstraintViolationException exception, final WebRequest webRequest) {
//    final ExceptionDetailsVO exDetailsVO = new ExceptionDetailsVO(exception.getMessage(), exception.getClass().getSimpleName(), webRequest);
//    return new ResponseEntity<>(exDetailsVO, HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler(AccessDeniedException.class)
//  public ResponseEntity<ExceptionDetailsVO> handleUnauthorizedException(final AccessDeniedException exception, final WebRequest webRequest) {
//    final ExceptionDetailsVO exceptionDetailsVO = new ExceptionDetailsVO(exception.getMessage(), exception.getClass().getSimpleName(), webRequest);
//    return new ResponseEntity<>(exceptionDetailsVO, HttpStatus.FORBIDDEN);
//  }
//
  // Auth exceptions
  @ExceptionHandler(AuthException.class)
  public ResponseEntity<Object> handleAuthException(final AuthException exception, WebRequest webRequest) {
    final ExceptionDetailsVO ex = new ExceptionDetailsVO(HttpStatus.BAD_REQUEST, "Invalid Authentication", exception);
    return buildResponseEntity(ex);
  }
//
//  @ExceptionHandler(ServletException.class)
//  public ResponseEntity<ExceptionDetailsVO> handleServletException(final ServletException exception, WebRequest webRequest) {
//    final ExceptionDetailsVO exceptionDetailsVO = new ExceptionDetailsVO(exception.getMessage(), exception.getClass().getSimpleName(), webRequest);
//    return new ResponseEntity<>(exceptionDetailsVO, HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler(JWTException.class)
//  public ResponseEntity<ExceptionDetailsVO> handleJWTException(final JWTException exception, final WebRequest webRequest) {
//    final ExceptionDetailsVO exceptionDetailsVO = new ExceptionDetailsVO(exception.getMessage(), exception.getClass().getSimpleName(), webRequest);
//    return new ResponseEntity<>(exceptionDetailsVO, HttpStatus.BAD_REQUEST);
////    return new ExceptionDetailsVO(ex.getMessage(), webRequest).getResponse();
//  }

  protected ResponseEntity<Object> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    final ExceptionDetailsVO details = new ExceptionDetailsVO(status, "Validation error", ex);
    details.addValidationErrors(ex.getBindingResult().getFieldErrors());
    details.addValidationError(ex.getBindingResult().getGlobalErrors());
    return buildResponseEntity(details);
  }
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//    Map<String, String> errors = new HashMap<>();
//    ex.getBindingResult().getAllErrors().forEach((error) -> {
//      String fieldName = ((FieldError) error).getField();
//      String message = error.getDefaultMessage();
//      errors.put(fieldName, message);
//    });
//    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//  }

  private static ResponseEntity<Object> buildResponseEntity(final ExceptionDetailsVO ex) {
    return new ResponseEntity<>(ex, ex.getStatus());
  }

  private static ResponseEntity<Object> buildResponseEntity(final ExceptionDetailsVO ex, final HttpHeaders headers) {
    return new ResponseEntity<>(ex, headers, ex.getStatus());
  }

  private static HttpStatus getStatus(final Exception ex) {
    if (ex.getClass().isAnnotationPresent(ResponseStatus.class)) {
      return ex.getClass().getAnnotation(ResponseStatus.class).code();
    } else if (ex instanceof AccessDeniedException || ex instanceof AuthenticationException) {
      return HttpStatus.FORBIDDEN;
    }

    final String exceptionName = ex.getClass().getSimpleName();

    if (StringUtils.endsWith(exceptionName, "NotFoundException")) {
      return HttpStatus.NOT_FOUND;
    } else if (ex instanceof NullPointerException) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    } else {
      return HttpStatus.BAD_REQUEST;
    }
  }

  private static String getError(final Exception ex) {
    if (ex instanceof AccessDeniedException || ex instanceof AuthenticationException) {
      return "The requested access to the resource was denied";
    }

    final String exceptionName = ex.getClass().getSimpleName();

    if (StringUtils.endsWith(exceptionName, "NotFoundException")) {
      return "Requested resource not found";
    } else {
      return "Unexpected error";
    }
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
