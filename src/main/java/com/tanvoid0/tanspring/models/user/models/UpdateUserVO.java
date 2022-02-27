package com.tanvoid0.tanspring.models.user.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import com.tanvoid0.tanspring.core.vo.BaseVO;

import java.util.HashSet;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserVO extends BaseVO {
  public static final String NAME = "UpdateUser";

  @Setter(AccessLevel.NONE)
  private final String _type = NAME;

  @NotNull
  private String username;

  @NotNull
  private String email;

  @NotNull
  private String password;

  private String name;

}
