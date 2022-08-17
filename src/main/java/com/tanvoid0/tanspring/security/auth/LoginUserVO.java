package com.tanvoid0.tanspring.security.auth;

import lombok.Data;

@Data
public class LoginUserVO {
  private String usernameOrEmail;
  private String password;
}
