package com.tanvoid0.tanspring.models.util_entities;

import com.tanvoid0.tanspring.common.exception.StaleVersionException;

public class ValidatorUtil {
  public static void staleVersionValidator(final long version1, final long version2) throws Exception {
    if (version1 != version2) {
      throw new StaleVersionException();
    }
  }
}
