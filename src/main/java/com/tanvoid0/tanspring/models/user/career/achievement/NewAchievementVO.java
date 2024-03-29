package com.tanvoid0.tanspring.models.user.career.achievement;

import com.tanvoid0.tanspring.models.user.career.organization.NewOrganizationVO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NewAchievementVO extends NewOrganizationVO {
  public static final String NAME = "NewAchievement";

  @Setter(AccessLevel.NONE)
  private final String _type = NAME;

  @NotNull
  private String achievement;
}

