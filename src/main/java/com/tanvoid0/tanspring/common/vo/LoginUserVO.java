package com.tanvoid0.tanspring.common.vo;

import lombok.Data;

@Data
public class LoginUserVO {
  private String usernameOrEmail;
  private String password;
}
