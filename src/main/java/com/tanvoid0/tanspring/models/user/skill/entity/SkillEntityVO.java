package com.tanvoid0.tanspring.models.user.skill.entity;

import com.tanvoid0.tanspring.models.user.skill.entity.hard.SkillHardVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.SkillFrameworkVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language.SkillLanguageVO;
import com.tanvoid0.tanspring.models.user.skill.entity.linguistic.SkillLinguisticVO;
import com.tanvoid0.tanspring.models.user.skill.entity.soft.SkillSoftVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SkillEntityVO {
  private Long id;
  private List<SkillLanguageVO> languages = new ArrayList<>();
  private Set<SkillFrameworkVO> frameworks = new HashSet<>();
  private Set<SkillHardVO> hardSkills = new HashSet<>();
  private Set<SkillSoftVO> softSkills = new HashSet<>();
  private Set<SkillLinguisticVO> linguisticSkills = new HashSet<>();
}
