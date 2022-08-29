package com.tanvoid0.tanspring.models.user.career.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrganizationVO {
    private Long id;

    // TODO: rest of the stuff
}
