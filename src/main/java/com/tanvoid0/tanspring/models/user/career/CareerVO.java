package com.tanvoid0.tanspring.models.user.career;

import com.tanvoid0.tanspring.common.vo.BaseVO;
import com.tanvoid0.tanspring.models.user.career.academic.AcademicVO;
import com.tanvoid0.tanspring.models.user.career.achievement.AchievementVO;
import com.tanvoid0.tanspring.models.user.career.certificate.CertificateVO;
import com.tanvoid0.tanspring.models.user.career.experience.ExperienceVO;
import com.tanvoid0.tanspring.models.user.career.volunteer.VolunteerVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CareerVO extends BaseVO {
  private List<AcademicVO> academics = new ArrayList<>();
  private List<AchievementVO> achievements = new ArrayList<>();
  private List<CertificateVO> certificates = new ArrayList<>();
  private List<ExperienceVO> experiences = new ArrayList<>();
  private List<VolunteerVO> volunteers = new ArrayList<>();
}
