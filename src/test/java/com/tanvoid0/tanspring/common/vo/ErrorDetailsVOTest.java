package com.tanvoid0.tanspring.common.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class ErrorDetailsVOTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link ErrorDetailsVO#ErrorDetailsVO(Date, String, String)}
   *   <li>{@link ErrorDetailsVO#getDetails()}
   *   <li>{@link ErrorDetailsVO#getMessage()}
   *   <li>{@link ErrorDetailsVO#getTimestamp()}
   * </ul>
   */
  @Test
  void testConstructor() {
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
    ErrorDetailsVO actualErrorDetailsVO = new ErrorDetailsVO(fromResult, "Not all who wander are lost", "Details");

    assertEquals("Details", actualErrorDetailsVO.getDetails());
    assertEquals("Not all who wander are lost", actualErrorDetailsVO.getMessage());
    assertSame(fromResult, actualErrorDetailsVO.getTimestamp());
  }
}

