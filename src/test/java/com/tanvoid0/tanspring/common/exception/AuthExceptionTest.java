package com.tanvoid0.tanspring.common.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class AuthExceptionTest {
  /**
   * Method under test: {@link AuthException#AuthException(HttpStatus, String)}
   */
  @Test
  void testConstructor() {
    AuthException actualAuthException = new AuthException(HttpStatus.CONTINUE, "0123456789ABCDEF");

    assertNull(actualAuthException.getCause());
    assertEquals(0, actualAuthException.getSuppressed().length);
    assertEquals("0123456789ABCDEF", actualAuthException.getMessage());
    assertEquals("0123456789ABCDEF", actualAuthException.getLocalizedMessage());
  }

  /**
   * Method under test: {@link AuthException#AuthException(String)}
   */
  @Test
  void testConstructor2() {
    AuthException actualAuthException = new AuthException("0123456789ABCDEF");
    assertNull(actualAuthException.getCause());
    assertEquals(0, actualAuthException.getSuppressed().length);
    assertEquals("0123456789ABCDEF", actualAuthException.getMessage());
    assertEquals("0123456789ABCDEF", actualAuthException.getLocalizedMessage());
  }
}

