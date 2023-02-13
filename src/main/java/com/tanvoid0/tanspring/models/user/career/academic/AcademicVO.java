package com.tanvoid0.tanspring.models.user.career.academic;

import com.tanvoid0.tanspring.models.user.career.organization.OrganizationVO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AcademicVO extends OrganizationVO {
  public static final String NAME = "Academic";

  @Setter(AccessLevel.NONE)
  private final String _type = NAME;

  @NotNull
  private String graduation;
}
