package com.tanvoid0.tanspring.models.user.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import com.tanvoid0.tanspring.core.vo.BaseVO;
import com.tanvoid0.tanspring.security.role.Role;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserVO extends BaseVO {
  public static final String BASE_NAME = "User";

  @Setter(value = AccessLevel.NONE)
  private final String _type = BASE_NAME;

  @NonNull
  private String username;

  @NonNull
  private String email;

  private String password;

  private String name;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Role> roles = new HashSet<>();
}
