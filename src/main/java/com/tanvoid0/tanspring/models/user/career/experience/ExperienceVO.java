package com.tanvoid0.tanspring.models.user.career.experience;

import com.tanvoid0.tanspring.models.user.career.organization.OrganizationVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceVO extends OrganizationVO {
    public static final String NAME = "Experience";

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String role;
}
