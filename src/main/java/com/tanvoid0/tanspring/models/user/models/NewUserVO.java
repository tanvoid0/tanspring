package com.tanvoid0.tanspring.models.user.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NewUserVO {

  public static final String NAME = "NewUser";

  @Setter(value = AccessLevel.NONE)
  private final String _type = NAME;

  @NotNull
  private String username;

  @NotNull
  private String email;

  @NotNull
  private String password;

  private String name;
}
