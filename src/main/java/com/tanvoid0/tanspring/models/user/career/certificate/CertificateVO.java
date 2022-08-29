package com.tanvoid0.tanspring.models.user.career.certificate;

import com.tanvoid0.tanspring.models.user.career.organization.OrganizationVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CertificateVO extends OrganizationVO {

    public static final String NAME = "Certificate";

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String graduation;

    @NotNull
    private String url;

    @NotNull
    private String certificate;
}
