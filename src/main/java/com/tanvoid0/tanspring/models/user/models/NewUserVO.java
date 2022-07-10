package com.tanvoid0.tanspring.models.user.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import com.tanvoid0.tanspring.security.role.ERole;
import com.tanvoid0.tanspring.security.role.Role;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NewUserVO {

  public static final String BASE_NAME = "NewUser";

  @Setter(value = AccessLevel.NONE)
  private final String _type = BASE_NAME;

  @NotNull
  private String username;

  @NotNull
  private String email;

  @NotNull
  private String password;

  private String name;

  Set<ERole> roles = new HashSet<>();
}
