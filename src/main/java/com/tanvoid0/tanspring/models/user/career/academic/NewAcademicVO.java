package com.tanvoid0.tanspring.models.user.career.academic;

import com.tanvoid0.tanspring.models.user.career.organization.NewOrganizationVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NewAcademicVO extends NewOrganizationVO {
    public static final String NAME = "NewAcademic";

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String graduation;
}

