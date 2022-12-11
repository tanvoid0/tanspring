package com.tanvoid0.tanspring.models.user.skill.entity.hard.framework;

import com.tanvoid0.tanspring.models.user.skill.entity.BaseSkillVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language.SkillLanguageVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.library.SkillLibraryVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SkillFrameworkVO extends BaseSkillVO {
  private Set<SkillLanguageVO> languages = new HashSet<>();
  private Set<SkillLibraryVO> libraries = new HashSet<>();
  private PlatformType platform = PlatformType.UNCATEGORIZED;
}
