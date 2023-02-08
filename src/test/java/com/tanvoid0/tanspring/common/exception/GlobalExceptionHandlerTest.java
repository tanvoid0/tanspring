package com.tanvoid0.tanspring.common.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tanvoid0.tanspring.common.vo.ExceptionDetailsVO;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

class GlobalExceptionHandlerTest {
  /**
   * Method under test: {@link GlobalExceptionHandler#handleResourceNotFoundException(ResourceNotFoundException, WebRequest)}
   */
  @Test
  void testHandleResourceNotFoundException() {
    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
    ResourceNotFoundException exception = new ResourceNotFoundException("Resource Name", "Field Name", "42");

    ResponseEntity<ExceptionDetailsVO> actualHandleResourceNotFoundExceptionResult = globalExceptionHandler
        .handleResourceNotFoundException(exception, new ServletWebRequest(new MockHttpServletRequest()));
    assertTrue(actualHandleResourceNotFoundExceptionResult.hasBody());
    assertTrue(actualHandleResourceNotFoundExceptionResult.getHeaders().isEmpty());
    assertEquals(HttpStatus.NOT_FOUND, actualHandleResourceNotFoundExceptionResult.getStatusCode());
    ExceptionDetailsVO body = actualHandleResourceNotFoundExceptionResult.getBody();
    assertEquals("uri=", body.getDetails());
    assertEquals("Resource Name not found with Field Name='42'", body.getMessage());
  }
//
//  /**
//   * Method under test: {@link GlobalExceptionHandler#handleBlogAPIException(ApiException, WebRequest)}
//   */
//  @Test
//  void testHandleBlogAPIException() {
//    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
//    ApiException exception = new ApiException(HttpStatus.CONTINUE, "An error occurred");
//
//    ResponseEntity<ExceptionDetailsVO> actualHandleBlogAPIExceptionResult = globalExceptionHandler
//        .handleException(exception, new ServletWebRequest(new MockHttpServletRequest()));
//    assertTrue(actualHandleBlogAPIExceptionResult.hasBody());
//    assertTrue(actualHandleBlogAPIExceptionResult.getHeaders().isEmpty());
//    assertEquals(HttpStatus.BAD_REQUEST, actualHandleBlogAPIExceptionResult.getStatusCode());
//    ExceptionDetailsVO body = actualHandleBlogAPIExceptionResult.getBody();
//    assertEquals("uri=", body.getDetails());
//    assertNull(body.getMessage());
//  }

  /**
   * Method under test: {@link GlobalExceptionHandler#handleGlobalException(Exception, WebRequest)}
   */
  @Test
  void testHandleGlobalException() {
    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
    Exception exception = new Exception("An error occurred");
    ResponseEntity<ExceptionDetailsVO> actualHandleGlobalExceptionResult = globalExceptionHandler
        .handleGlobalException(exception, new ServletWebRequest(new MockHttpServletRequest()));
    assertTrue(actualHandleGlobalExceptionResult.hasBody());
    assertTrue(actualHandleGlobalExceptionResult.getHeaders().isEmpty());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualHandleGlobalExceptionResult.getStatusCode());
    ExceptionDetailsVO body = actualHandleGlobalExceptionResult.getBody();
    assertEquals("uri=", body.getDetails());
    assertEquals("An error occurred", body.getMessage());
  }

  /**
   * Method under test: {@link GlobalExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException, HttpHeaders, HttpStatus, WebRequest)}
   */
  @Test
  void testHandleMethodArgumentNotValid() {
    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
    MethodParameter parameter = new MethodParameter(
        new MethodParameter(new MethodParameter(new MethodParameter(mock(MethodParameter.class)))));
    MethodArgumentNotValidException ex = new MethodArgumentNotValidException(parameter,
        new BindException(new BindException(
            new BindException(new BindException(new BeanPropertyBindingResult("Target", "Object Name"))))));

    HttpHeaders headers = new HttpHeaders();
    ResponseEntity<Object> actualHandleMethodArgumentNotValidResult = globalExceptionHandler
        .handleMethodArgumentNotValid(ex, headers, HttpStatus.CONTINUE,
            new ServletWebRequest(new MockHttpServletRequest()));
    assertTrue(actualHandleMethodArgumentNotValidResult.hasBody());
    assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
    assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link GlobalExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException, HttpHeaders, HttpStatus, WebRequest)}
   */
  @Test
  void testHandleMethodArgumentNotValid2() {
    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>());
    BindException bindingResult1 = new BindException(
        new BindException(new BindException(new BindException(new BindException(
            new BindException(new BindException(new BindException(new BindException(bindingResult)))))))));
    MethodArgumentNotValidException ex = new MethodArgumentNotValidException(
        new MethodParameter(new MethodParameter(new MethodParameter(new MethodParameter(mock(MethodParameter.class))))),
        bindingResult1);

    HttpHeaders headers = new HttpHeaders();
    ResponseEntity<Object> actualHandleMethodArgumentNotValidResult = globalExceptionHandler
        .handleMethodArgumentNotValid(ex, headers, HttpStatus.CONTINUE,
            new ServletWebRequest(new MockHttpServletRequest()));
    assertTrue(actualHandleMethodArgumentNotValidResult.hasBody());
    assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
    assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
    verify(bindingResult).getAllErrors();
  }
}

