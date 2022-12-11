package com.tanvoid0.tanspring.models.user.skill.entity.hard.framework;

import com.tanvoid0.tanspring.common.template.CustomService;
import com.tanvoid0.tanspring.models.user.skill.entity.BaseSkillVO;
import com.tanvoid0.tanspring.models.user.skill.entity.UpdateSkillVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.NewSkillItemVO;

import java.util.List;

public interface SkillFrameworkService extends CustomService<SkillFramework, SkillFrameworkVO, NewSkillFrameworkVO, UpdateSkillVO> {
  List<BaseSkillVO> addLanguage(List<NewSkillItemVO> newSkillLanguageVOs);

  BaseSkillVO addLanguage(NewSkillItemVO newVO);

  SkillFrameworkVO linkLanguage(long id, long languageId);

  SkillFrameworkVO linkLanguageRemove(long id, long languageId);
}
