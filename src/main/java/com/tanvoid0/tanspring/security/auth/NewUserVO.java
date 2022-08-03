package com.tanvoid0.tanspring.security.auth;

import lombok.Data;

@Data
public class NewUserVO {
  private String name;
  private String username;
  private String email;
  private String password;
}
