//package com.tanvoid0.tanspring.config;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.Test;
//
//import springfox.documentation.spring.web.plugins.Docket;
//
//class SwaggerConfigTest {
//  /**
//   * Method under test: {@link SwaggerConfig#api()}
//   */
//  @Test
//  void testApi() {
//    //   Diffblue Cover was unable to write a Spring test,
//    //   so wrote a non-Spring test instead.
//    //   Reason: R004 No meaningful assertions found.
//    //   Diffblue Cover was unable to create an assertion.
//    //   Make sure that fields modified by api()
//    //   have package-private, protected, or public getters.
//    //   See https://diff.blue/R004 to resolve this issue.
//
//    Docket actualApiResult = (new SwaggerConfig()).api();
//    assertTrue(actualApiResult.isEnabled());
//    assertEquals("default", actualApiResult.getGroupName());
//  }
//}
//
