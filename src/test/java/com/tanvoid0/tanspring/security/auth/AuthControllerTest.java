//package com.tanvoid0.tanspring.security.auth;
//
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.tanvoid0.tanspring.common.vo.JWTAuthResponseVO;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ContextConfiguration(classes = {AuthController.class})
//@ExtendWith(SpringExtension.class)
//@PropertySource("classpath:application-test.properties")
//@EnableConfigurationProperties
//class AuthControllerTest {
//    @Autowired
//    private AuthController authController;
//
//    @MockBean
//    private AuthenticationManager authenticationManager;
//
//    @MockBean
//    private UserService userService;
//
//    /**
//     * Method under test: {@link AuthController#authenticateUser(LoginUserVO)}
//     */
//    @Test
//    @Disabled
//    void testAuthenticateUser() throws Exception {
//        when(userService.login((LoginUserVO) any())).thenReturn(new JWTAuthResponseVO("ABC123"));
//
//        LoginUserVO loginUserVO = new LoginUserVO();
//        loginUserVO.setPassword("iloveyou");
//        loginUserVO.setUsernameOrEmail("janedoe");
//        String content = (new ObjectMapper()).writeValueAsString(loginUserVO);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/signin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(authController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("{\"accessToken\":\"ABC123\",\"tokenType\":\"Bearer\"}"));
//    }
//
//    /**
//     * Method under test: {@link AuthController#registerUser(NewUserVO)}
//     */
//    @Test
//    @Disabled
//    void testRegisterUser() throws Exception {
//        when(userService.register((NewUserVO) any())).thenReturn(new UserVO());
//
//        NewUserVO newUserVO = new NewUserVO();
//        newUserVO.setEmail("jane.doe@example.org");
//        newUserVO.setName("Name");
//        newUserVO.setPassword("iloveyou");
//        newUserVO.setUsername("janedoe");
//        String content = (new ObjectMapper()).writeValueAsString(newUserVO);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/signup")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
//    }
//}
//
