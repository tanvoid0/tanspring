//package com.tanvoid0.tanspring.security.jwt;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import com.tanvoid0.tanspring.common.exception.ApiException;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@ContextConfiguration(classes = {JwtUtil.class})
//@ExtendWith(SpringExtension.class)
//class JwtUtilTest {
//  @Autowired
//  private JwtUtil jwtUtil;
//
//  /**
//   * Method under test: {@link JwtUtil#getJWTTokenFromRequest(HttpServletRequest)}
//   */
//  @Test
//  @Disabled("TODO: Complete this test")
//  void testGetJWTTokenFromRequest() {
//    // TODO: Complete this test.
//    //   Reason: F009 Internal error.
//    //   java.lang.StackOverflowError
//    //   Please contact Diffblue through the appropriate support channel
//    //   (https://www.diffblue.com/support/) providing details about this error.
//
//    // Arrange
//    // TODO: Populate arranged inputs
//    HttpServletRequest request = null;
//
//    // Act
//    String actualJWTTokenFromRequest = JwtUtil.getJWTTokenFromRequest(request);
//
//    // Assert
//    // TODO: Add assertions on result
//  }
//
//  /**
//   * Method under test: {@link JwtUtil#validateToken(String, String)}
//   */
//  @Disabled
//  @Test
//  void testValidateToken() {
//    assertThrows(ApiException.class, () -> JwtUtil.validateToken("123", "ABC123"));
//  }
//
//  /**
//   * Method under test: {@link JwtUtil#getUsernameFromJWT(String, String)}
//   */
//  @Test
//  @Disabled("TODO: Complete this test")
//  void testGetUsernameFromJWT() {
//    // TODO: Complete this test.
//    //   Reason: R013 No inputs found that don't throw a trivial exception.
//    //   Diffblue Cover tried to run the arrange/act section, but the method under
//    //   test threw
//    //   java.lang.IllegalArgumentException: signing key cannot be null or empty.
//    //       at io.jsonwebtoken.lang.Assert.hasText(Assert.java:135)
//    //       at io.jsonwebtoken.impl.DefaultJwtParser.setSigningKey(DefaultJwtParser.java:150)
//    //       at com.tanvoid0.tanspring.security.jwt.JwtUtil.getUsernameFromJWT(JwtUtil.java:70)
//    //   In order to prevent getUsernameFromJWT(String)
//    //   from throwing IllegalArgumentException, add constructors or factory
//    //   methods that make it easier to construct fully initialized objects used in
//    //   getUsernameFromJWT(String).
//    //   See https://diff.blue/R013 to resolve this issue.
//
//    JwtUtil.getUsernameFromJWT("123", "ABC123");
//  }
//}
//
