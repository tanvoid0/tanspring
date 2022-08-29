package com.tanvoid0.tanspring.models.user.career.organization;

import com.tanvoid0.tanspring.common.vo.BaseVO;
import com.tanvoid0.tanspring.models.user.career.Career;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "_type"
//)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = NewAcademicVO.class, name = NewAcademicVO.NAME),
//        @JsonSubTypes.Type(value = NewAchievementVO.class, name = NewAchievementVO.NAME),
//        @JsonSubTypes.Type(value = NewCertificateVO.class, name = NewCertificateVO.NAME),
//        @JsonSubTypes.Type(value = NewExperienceVO.class, name = NewExperienceVO.NAME),
//        @JsonSubTypes.Type(value = NewVolunteerVO.class, name = NewVolunteerVO.NAME)
//})
public abstract class NewOrganizationVO extends BaseVO {
    @NotNull
    private String title;

    private String image;
    private String logo;
    private String institution;
    private String address;
    private String timeline;
    private String description;
    private String activities;
}
