package com.tanvoid0.tanspring.models.user.career.achievement;

import com.tanvoid0.tanspring.models.user.career.organization.OrganizationVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

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
