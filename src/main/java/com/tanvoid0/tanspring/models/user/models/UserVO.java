package com.tanvoid0.tanspring.models.user.models;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import com.tanvoid0.tanspring.core.vo.BaseVO;

import java.util.HashSet;
import java.util.Set;

public class UserVO extends BaseVO {
  public static final String NAME = "User";

  @Setter(value = AccessLevel.NONE)
  private final String _type = NAME;

  private String username;

  private String email;

  private String password;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Role> roles = new HashSet<>();
}
