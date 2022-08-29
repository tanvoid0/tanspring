package com.tanvoid0.tanspring.models.user.career;

import com.tanvoid0.tanspring.common.vo.BaseVO;
import com.tanvoid0.tanspring.models.user.career.academic.Academic;
import com.tanvoid0.tanspring.models.user.career.achievement.Achievement;
import com.tanvoid0.tanspring.models.user.career.certificate.Certificate;
import com.tanvoid0.tanspring.models.user.career.experience.Experience;
import com.tanvoid0.tanspring.models.user.career.volunteer.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CareerVO extends BaseVO {
  private Set<Academic> academics = new HashSet<>();
  private Set<Achievement> achievements = new HashSet<>();
  private Set<Certificate> certificates = new HashSet<>();
  private Set<Experience> experiences = new HashSet<>();
  private Set<Volunteer> volunteers = new HashSet<>();
}
