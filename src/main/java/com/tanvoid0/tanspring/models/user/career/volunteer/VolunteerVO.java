package com.tanvoid0.tanspring.models.user.career.volunteer;

import com.tanvoid0.tanspring.models.user.career.organization.OrganizationVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerVO extends OrganizationVO {
    public static final String NAME = "Volunteer";

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    private String role;
}
