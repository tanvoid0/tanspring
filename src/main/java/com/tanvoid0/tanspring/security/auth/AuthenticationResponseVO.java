package com.tanvoid0.tanspring.security.auth;

import com.tanvoid0.tanspring.models.user.UserVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AuthenticationResponseVO extends UserVO {
  private String token;
  private final String tokenType = "Bearer";

  private Date expiresIn;
}
