package com.tanvoid0.tanspring.models.user.career.volunteer;

import com.tanvoid0.tanspring.models.user.career.organization.NewOrganizationVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NewVolunteerVO extends NewOrganizationVO {
    public static final String NAME = "NewVolunteer";

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    private String role;
}
