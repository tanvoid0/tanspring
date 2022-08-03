package com.tanvoid0.tanspring.common.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {
  /**
   * Method under test: {@link ResourceNotFoundException#ResourceNotFoundException(String, String, long)}
   */
  @Test
  void testConstructor() {
    ResourceNotFoundException actualResourceNotFoundException = new ResourceNotFoundException("Resource Name",
        "Field Name", 42L);

    assertEquals("Resource Name", actualResourceNotFoundException.getResourceName());
    assertEquals("42", actualResourceNotFoundException.getFieldValue());
    assertEquals("Field Name", actualResourceNotFoundException.getFieldName());
  }
}

