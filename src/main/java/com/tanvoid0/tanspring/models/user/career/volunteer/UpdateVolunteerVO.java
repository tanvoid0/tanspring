package com.tanvoid0.tanspring.models.user.career.volunteer;

import com.tanvoid0.tanspring.models.user.career.organization.UpdateOrganizationVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVolunteerVO extends UpdateOrganizationVO {
    private String role;
}
