package com.tanvoid0.tanspring.models.user.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import com.tanvoid0.tanspring.core.vo.BaseVO;
import com.tanvoid0.tanspring.security.role.Role;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserVO extends BaseVO {
  public static final String BASE_NAME = "UpdateUser";

  @Setter(AccessLevel.NONE)
  private final String _type = BASE_NAME;

  @NotNull
  private String username;

  @NotNull
  private String email;

  @NotNull
  private String password;

  private String name;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Role> roles = new HashSet<>();

}
