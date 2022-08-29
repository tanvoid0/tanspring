package com.tanvoid0.tanspring.models.user.career.certificate;

import com.tanvoid0.tanspring.models.user.career.organization.NewOrganizationVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NewCertificateVO extends NewOrganizationVO {

    public static final String NAME = "NewCertificate";

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    private String graduation;

    private String url;

    private String certificate;
}
