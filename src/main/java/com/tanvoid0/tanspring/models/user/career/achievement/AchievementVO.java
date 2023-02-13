package com.tanvoid0.tanspring.models.user.career.achievement;

import com.tanvoid0.tanspring.models.user.career.organization.OrganizationVO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AchievementVO extends OrganizationVO {
  public static final String NAME = "Achievement";

  @Setter(AccessLevel.NONE)
  private final String _type = NAME;

  @NotNull
  private String achievement;
}
