package com.tanvoid0.tanspring.models.user.career.certificate;

import com.tanvoid0.tanspring.models.user.career.organization.UpdateOrganizationVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCertificateVO extends UpdateOrganizationVO {

    private String graduation;

    private String url;

    private String certificate;
}
