//package com.tanvoid0.tanspring.common.vo;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
//
//import com.tanvoid0.tanspring.common.exception.ExceptionDetailsVO;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//
//import org.junit.jupiter.api.Test;
//
//class ExceptionDetailsVOTest {
//  /**
//   * Methods under test:
//   *
//   * <ul>
//   *   <li>{@link ExceptionDetailsVO#ExceptionDetailsVO(Date, String, String)}
//   *   <li>{@link ExceptionDetailsVO#getDetails()}
//   *   <li>{@link ExceptionDetailsVO#getMessage()}
//   *   <li>{@link ExceptionDetailsVO#getTimestamp()}
//   * </ul>
//   */
//  @Test
//  void testConstructor() {
//    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
//    Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
//    ExceptionDetailsVO actualExceptionDetailsVO = new ExceptionDetailsVO(fromResult, "Not all who wander are lost", "Details");
//
//    assertEquals("Details", actualExceptionDetailsVO.getDetails());
//    assertEquals("Not all who wander are lost", actualExceptionDetailsVO.getMessage());
//    assertSame(fromResult, actualExceptionDetailsVO.getTimestamp());
//  }
//}
//
