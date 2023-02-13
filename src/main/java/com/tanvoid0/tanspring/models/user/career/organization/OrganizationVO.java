package com.tanvoid0.tanspring.models.user.career.organization;

import com.tanvoid0.tanspring.common.vo.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;

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
//        @Type(value = AcademicVO.class, name = Academic.NAME),
//        @Type(value = AchievementVO.class, name = Achievement.NAME),
//        @Type(value = CertificateVO.class, name = Certificate.NAME),
//        @Type(value = ExperienceVO.class, name = Experience.NAME),
//        @Type(value = VolunteerVO.class, name = Volunteer.NAME)
//})
public class OrganizationVO extends BaseVO {
  @NotNull
  private String title;
  private String image;
  private String logo;
  private String institution;
  private String address;
  private String timeline;
  private String description;
  private String activities;
  private Long orderSeq;
}
